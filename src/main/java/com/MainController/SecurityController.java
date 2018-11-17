package com.MainController;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.web.*;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import com.DaoClasses.userDaoImpl;
import com.DaoClasses.usersDao;
import com.EntityClasses.User_Info;
import com.ModelClasses.UserModel;

@Controller
public class SecurityController {

	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String defaultPage() {

		ModelAndView model = new ModelAndView();


		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 if(!(auth.getPrincipal()=="anonymousUser")){
			 UserDetails userDetails=(UserDetails) auth.getPrincipal();
			 Map<String ,Object> role= new HashMap<String ,Object>();
			 role.put("role1",userDetails.getAuthorities());
			 Object role2=(Object) role.get("role1");
			 StringBuffer userRole=new StringBuffer(role2.toString());
			 System.out.print(userRole);
			 if ("[ROLE_ADMIN]".contentEquals(userRole))
			 {
				 return "redirect:current_schedule";
			 }
			 if ("[ROLE_CUSTOMER]".contentEquals(userRole))
			 {
				 return "redirect:customer";
			 }
			 if ("[ROLE_STUDENT]".contentEquals(userRole))
			 {
				 return "redirect:student";
			 }
		 }
			 return "redirect:login";
		 
		 
		 
		
		

	}


	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {
		
		
		 
		ModelAndView model = new ModelAndView();
		
		
		if (error != null) {
			model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(!(auth.getPrincipal()=="anonymousUser")){
			 UserDetails userDetails=(UserDetails) auth.getPrincipal();
			 Map<String ,Object> role= new HashMap<String ,Object>();
			 role.put("role1",userDetails.getAuthorities());
			 Object role2=(Object) role.get("role1");
			 StringBuffer userRole=new StringBuffer(role2.toString());
			 if ("[ROLE_ADMIN]".contentEquals(userRole))
			 {
				 model.setViewName("redirect:current_schedule");
				 return model;
			 }
			 if ("[ROLE_CUSTOMER]".contentEquals(userRole))
			 {
				 model.setViewName("redirect:customer");
				 return model;
			 }
			 if ("[ROLE_STUDENT]".contentEquals(userRole))
			 {
				 model.setViewName("redirect:student");
				 return model;
			 }
		 }
		
		model.setViewName("login");
		return model;

	}
	@RequestMapping(value = "/ie", method = RequestMethod.GET)
	public ModelAndView IE() {
		ModelAndView modelAndView = new ModelAndView("ie");
		return modelAndView;
	}

	// customize the error message
	private String getErrorMessage(HttpServletRequest request, String key) {

		Exception exception = (Exception) request.getSession().getAttribute(key);

		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = "Invalid username and password!";
		} else if (exception instanceof LockedException) {
			error = exception.getMessage();
		} else {
			error = "Invalid username and password!";
		}

		return error;
	}
	

	// for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(!(auth.getPrincipal()=="anonymousUser")){
			UserDetails userDetails=(UserDetails) auth.getPrincipal();
			Map<String ,Object> role= new HashMap<String ,Object>();
			role.put("role1",userDetails.getAuthorities());
			Object role2=(Object) role.get("role1");
			StringBuffer userRole=new StringBuffer(role2.toString());
			if ("[ROLE_ADMIN]".contentEquals(userRole))
			{
				model.setViewName("redirect:current_schedule");
				return model;
			}
			if ("[ROLE_CUSTOMER]".contentEquals(userRole))
			{
				model.setViewName("redirect:customer");
				return model;
			}
			if ("[ROLE_STUDENT]".contentEquals(userRole))
			{
				model.setViewName("redirect:student");
				return model;
			}
		}
		model.setViewName("login");
		return model;

	}
	@RequestMapping(value = "/check_signup", method = RequestMethod.POST)
	@ResponseBody public Map<String,Object> checkSignup(@RequestBody UserModel user) {
		usersDao userdao = new userDaoImpl();
		boolean status = true;
		Map<String,Object> map = new HashMap<String,Object>();
		System.out.println("hello:"+user.getPassword());
		User_Info user_info = userdao.findByUserName(user.getEmail());
		
		if(user_info==null){
			status = userdao.createUser(user,"google");
		}
		else {
			if(user_info.getGooglePassword()==null){
				status =userdao.updateUser(user_info, user,"google");
			}
		}
		map.put("status", status);
		return map;

	}
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	@ResponseBody public Map<String,Object> signup(@RequestBody UserModel user) {
		usersDao userdao = new userDaoImpl();
		boolean status = true;
		Map<String,Object> map = new HashMap<String,Object>();
		User_Info user_info = userdao.findByUserName(user.getEmail());
		System.out.println();
		if(user_info==null){
			System.out.println("create");
			status = userdao.createUser(user,"stystem");
		}else{
			System.out.println("update");
			status =userdao.updateUser(user_info, user,"system");
		}
		map.put("status", status);
		return map;

	}
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public String handleError405(HttpServletRequest request, Exception e) {
		System.out.println("not support");
		return "redirect:/login";
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleResourceNotFoundException() {
		return "security/403";
	}

}
