package com.LoginFilter;

import getInfoLogin.IdUser;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import javax.servlet.http.*;
public class CookieFilter implements javax.servlet.Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        String header = ((HttpServletRequest) req).getHeader("ajax");
        String path = ((HttpServletRequest) req).getRequestURL().toString();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(header!=null){
            System.out.println(path);
            System.out.println(auth.getAuthorities().toString());
            System.out.println("header : " +header);

            if(( auth.getAuthorities().toString()).equals("[ROLE_ANONYMOUS]")){
                HttpServletResponse httpResponse = (HttpServletResponse) resp;
                httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
                return;
            }else{
                chain.doFilter(req, resp);
            }
        }else{
            chain.doFilter(req, resp);
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
