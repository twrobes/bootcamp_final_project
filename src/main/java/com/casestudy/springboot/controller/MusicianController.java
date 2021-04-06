package com.casestudy.springboot.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.springboot.entities.Musician;
import com.casestudy.springboot.service.MusicianService;

@RestController
public class MusicianController {
	
	private final MusicianService musicianService;
	
	@Autowired
	public MusicianController(MusicianService musicianService) {
		this.musicianService = musicianService;
	}
	
	@GetMapping("/musician")
	public List<Musician> getMusicians() {
		return musicianService.getMusicians();
	}

	/*
	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World", required = true) String name, Model model) {
		List<Musician> music = List.of(
				new Musician(
						1,
						"Thomas",
						"Wrobel",
						"test@email",
						24
						)
				);
		String musicString = music.toString();
		System.out.println(musicString);
		
		model.addAttribute(musicString, name);
		
		return "hello";
	}
	*/
}
