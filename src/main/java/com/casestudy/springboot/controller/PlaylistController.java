package com.casestudy.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.springboot.entities.Playlist;
import com.casestudy.springboot.service.PlaylistService;

@RestController
public class PlaylistController {
	
	private final PlaylistService playlistService;
	
	@Autowired
	public PlaylistController(PlaylistService playlistService) {
		this.playlistService = playlistService;
	}
	
	@GetMapping("/playlist")
	public List<Playlist> getMusicians() {
		return playlistService.getPlaylists();
	}
	
}
