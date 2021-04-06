package com.casestudy.springboot.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.casestudy.springboot.service.MusicianService;
import com.casestudy.springboot.service.UserService;

@Controller
public class HomeController {
	
	@Autowired
	public UserService userService;
	
	@Autowired
	public MusicianService musicianService;
	
	@RequestMapping(value="home", method=RequestMethod.GET)
	public ModelAndView home(UserService userService) {
		ModelAndView mav = new ModelAndView("home");
		//mav.addObject("firstname", user.getMusician().getfName());
		return mav;
	}
	
	
	@RequestMapping(value="homeTest", method=RequestMethod.GET)
	public ModelAndView showUsers() {
		ModelAndView mav = new ModelAndView("home");
		mav.addObject("users", userService.getUsers());
		return mav;
	}
}
