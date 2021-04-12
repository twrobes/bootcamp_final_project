package com.casestudy.springboot.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.casestudy.springboot.entities.Songs;
import com.casestudy.springboot.service.MusicianService;
import com.casestudy.springboot.service.PlaylistService;
import com.casestudy.springboot.service.SongsService;
import com.casestudy.springboot.service.UserService;

@RestController
public class ScoreController {
	
	@Autowired
	public UserService userService;
	
	@Autowired
	public MusicianService musicianService;
	
	@Autowired
	public PlaylistService playlistService;
	
	@Autowired
	public SongsService songsService;
	
	@GetMapping("addsong")
	public ModelAndView addSong() {
		ModelAndView mav = new ModelAndView("addsong");
		
		return mav;
	}
	
	@PostMapping("addsong")
	public ModelAndView addNewSong(@RequestParam(name = "artist") String songArtist,
								   @RequestParam(name = "title") String songTitle,
								   @RequestParam(name = "scoreId") String scoreId) {
		
		ModelAndView mav = new ModelAndView("addsong");
		Songs newSong = new Songs(songArtist, songTitle, scoreId);
		ArrayList<String> array = new ArrayList<>();
		
		songsService.getSongs().forEach(s -> array.add(s.getScore()));
		
		if (!(array.contains(scoreId))) {
			songsService.addSong(newSong);
		}
		else {
			mav.addObject("error", "Error: Song already add to the website! Please add a different song");
			mav.setViewName("/songerror");
			return mav;
		}
		
		mav.setViewName("redirect:/addsong");
		return mav;
	}
	
	@PostMapping("gotoscore")
	public ModelAndView showScore(@RequestParam(name = "songId") Integer songId) {
		ModelAndView mav = new ModelAndView("score");
		Songs scoreId = songsService.findSongById(songId);
//		String scoreStr = "https://flat.io/embed/" + scoreId.getScore() + "?appId=606f341b7e57ee428a43121b";
		
		mav.setViewName("score");
		mav.addObject("score", scoreId.getScore());
		return mav;
	}
}
