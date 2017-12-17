package cz.muni.fi.pa165.mvc.security;

import cz.muni.fi.pa165.soccermanager.dto.ManagerDTO;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 445720 Martin Hamernik
 * @version 12/8/2017.
 */
@WebFilter(urlPatterns =  {"/team/new/", "/player/new/", "/league/new/", "/match/new/", "/manager/new/",
        "/team/new", "/player/new", "/league/new", "/match/new", "/manager/new",
        "/team/delete/*", "/player/delete/*", "/league/delete/*", "/match/delete/*", "/manager/delete/*"})
public class AdminFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        ManagerDTO manager = (ManagerDTO) request.getSession().getAttribute("authenticatedUser");

        if (manager == null) {
            response.sendRedirect(request.getContextPath() + "/auth/login");
            return;
        }

        if (!manager.isAdmin()) {
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }

        filterChain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
