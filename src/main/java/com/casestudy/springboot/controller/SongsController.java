package com.casestudy.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.springboot.entities.Songs;
import com.casestudy.springboot.service.SongsService;


@RestController
public class SongsController {

	private final SongsService songsService;
	
	@Autowired
	public SongsController(SongsService songsService) {
		this.songsService = songsService;
	}
	
	@GetMapping("/songs")
	public List<Songs> getMusicians() {
		return songsService.getSongs();
	}
	
}
