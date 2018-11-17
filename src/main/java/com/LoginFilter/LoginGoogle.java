package com.LoginFilter;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet(name = "myServlet", urlPatterns = "/googleUser")
public class LoginGoogle extends HttpServlet {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

@Override
  protected void doPost (HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
	ServletContext application = getServletConfig().getServletContext();
	
	String password = req.getParameter("password");
	System.out.println(password);
  
  }
}
