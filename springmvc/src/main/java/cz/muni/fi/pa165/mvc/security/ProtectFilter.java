package cz.muni.fi.pa165.mvc.security;

import javax.servlet.annotation.WebFilter;

/**
 * @author 445720 Martin Hamernik
 * @version 12/1/2017.
 */

@WebFilter(urlPatterns =  {"/team/*", "/m/*", ""})
public class ProtectFilter {
}
