package com.LoginFilter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DaoClasses.userDaoImpl;
import com.DaoClasses.usersDao;
import com.EntityClasses.User_Info;
import com.ModelClasses.IdentifyTypeUser;


//@WebFilter(urlPatterns = {"/googleUser"}, description = "Session Checker CookieFilter")
public class LoginFilterClass implements Filter {

	 public void init(FilterConfig filterConfig) throws ServletException {
	        //
	    }
   

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	 HttpServletRequest httpRequest = (HttpServletRequest) request;  
    	 String email = httpRequest.getParameter("email"); 
    	 chain.doFilter(request, response);
    }


	public void destroy() {
		// TODO Auto-generated method stub
		
	}
    
   

	
}