package com.casestudy.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.casestudy.springboot.entities.User;
import com.casestudy.springboot.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	public UserService userService;
	
	// to get login form page
	@RequestMapping(value="login", method=RequestMethod.GET)
	public String getLoginForm() {
		//return html page name
		return "login";
	}
	
//	//checking for login credentials
//	@RequestMapping(value="login", method=RequestMethod.POST)
//	public String login(@ModelAttribute(name="loginForm") User user, Model model) {
//		
//		String username = user.getUsername();
//		String password = user.getPassword();
//		
//		if (username.equals("admin") && password.equals("admin"))
//			return "home";
//		else if (userService.userIsValid(username, password)) {
//			// if username and password is correct, we are returning to home page
//			return "home";
//		}
//			
//		// if username or password is wrong
//		model.addAttribute("invalidCredentials", true);
//		// returning back to login page
//		
//		return "login";
//		
//	}
}
