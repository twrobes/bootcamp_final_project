package com.casestudy.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.casestudy.springboot.entities.User;
import com.casestudy.springboot.service.MusicianService;
import com.casestudy.springboot.service.PlaylistService;
import com.casestudy.springboot.service.SongsService;
import com.casestudy.springboot.service.UserService;

@RestController
public class HomeController {
	
	@Autowired
	public UserService userService;
	
	@Autowired
	public MusicianService musicianService;
	
	@Autowired
	public PlaylistService playlistService;
	
	@Autowired
	public SongsService songsService;
	
	@RequestMapping(value="home", method=RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView mav = new ModelAndView("home");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User currentUser = userService.getUserByUsername(auth.getName());
		
		mav.addObject("firstname", currentUser.getMusician().getfName());
		mav.addObject("totalsongs", songsService.getTotalSongs());
		mav.addObject("totalplaylists", playlistService.getTotalPlaylists());
		mav.addObject("totalusers", userService.getTotalUsers());
		return mav;
	}
	
}
