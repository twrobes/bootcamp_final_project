package com.casestudy.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.casestudy.springboot.entities.Musician;
import com.casestudy.springboot.entities.User;
import com.casestudy.springboot.service.MusicianService;
import com.casestudy.springboot.service.UserService;

@Controller
public class RegistrationController {
	
	@Autowired
	public UserService userService;
	
	@Autowired
	public MusicianService musicianService;
	
	@RequestMapping(value="registration", method=RequestMethod.GET)
	public String getRegistrationForm() {
		return "registration";
	}
	
	@RequestMapping(value="registration", method=RequestMethod.POST)
	public ModelAndView register(@ModelAttribute(name="registration") User user, Musician musician, Model model) {
		
		String firstname = musician.getfName();
		String lastname = musician.getlName();
		Integer age = musician.getAge();
		String username = user.getUsername();
		String email = user.getEmail();
		String password = user.getPassword();
		
		user.setMusician(musician);
		userService.addNewUser(user);
		
		return new ModelAndView("redirect:/index");
	}
}