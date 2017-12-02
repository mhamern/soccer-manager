package cz.muni.fi.pa165.mvc.security;

import cz.muni.fi.pa165.soccermanager.dto.ManagerDTO;
import cz.muni.fi.pa165.soccermanager.facade.ManagerFacade;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;

/**
 * @author 445720 Martin Hamernik
 * @version 12/1/2017.
 */

//@WebFilter(urlPatterns =  {"/team/*", "/player/*", "/match/*", "/league/*", "/manager/*"}) resolve problem with auth
public class ProtectFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String auth = request.getHeader("Authorization");
        if (auth == null) {
            response401(response);
            return;
        }

        String[] creds = parseAuthHeader(auth);
        String logname = creds[0];
        String password = creds[1];

        ManagerFacade managerFacade = WebApplicationContextUtils
                .getWebApplicationContext(request.getServletContext()).getBean(ManagerFacade.class);
        ManagerDTO matchingManager = managerFacade.findManagerByEmail(logname);

        if (matchingManager == null) {
            response401(response);
            return;
        }

//        ManagerAuthenticateDTO managerAuthenticateDTO = new ManagerAuthenticateDTO();
//        managerAuthenticateDTO.setId(matchingManager.getId());
//        managerAuthenticateDTO.setPassword(password);
//
//        if (!managerFacade.isAdmin(matchingManager)) {
//            response401(response);
//            return;
//        }
//
//        if (!managerFacade.authenticate(managerAuthenticateDTO)) {
//            response401(response);
//            return;
//        }

        request.setAttribute("authenticatedUser", matchingManager);
        filterChain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    private String[] parseAuthHeader(String auth) {
        return new String(DatatypeConverter.parseBase64Binary(auth.split(" ")[1])).split(":", 2);
    }

    private void response401(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setHeader("WWW-Authenticate", "Basic realm=\"type email and password\"");
        response.getWriter().println("<html><body><h1>401 Unauthorized</h1> Go away ...</body></html>");
    }
}
