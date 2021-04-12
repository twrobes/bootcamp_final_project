package com.casestudy.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.casestudy.springboot.entities.Playlist;
import com.casestudy.springboot.entities.Songs;
import com.casestudy.springboot.entities.User;
import com.casestudy.springboot.service.MusicianService;
import com.casestudy.springboot.service.PlaylistService;
import com.casestudy.springboot.service.SongsService;
import com.casestudy.springboot.service.UserService;

@RestController
public class PlaylistController {
	
	@Autowired
	public UserService userService;
	
	@Autowired
	public MusicianService musicianService;
	
	@Autowired
	public SongsService songsService;
	
	private final PlaylistService playlistService;
	
	@Autowired
	public PlaylistController(PlaylistService playlistService) {
		this.playlistService = playlistService;
	}
	
	@GetMapping("myplaylists")
	public Model getAllPlaylists(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User currentUser = userService.getUserByUsername(auth.getName());
		List<Playlist> userPlaylists = musicianService.getMusicianPlaylists(currentUser.getMusician());
		
		model.addAttribute("playlists", userPlaylists);
		
		return model;
	}
	
	@GetMapping("/playlists/addtoplaylist")
	public Model getAddToPlaylist(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User currentUser = userService.getUserByUsername(auth.getName());
		List<Playlist> userPlaylists = currentUser.getMusician().getPlaylists();
		
		model.addAttribute("songs", songsService.getSongs());
		model.addAttribute("playlists", userPlaylists);
		
		return model;
	}
	
	@PostMapping("/playlists/addtoplaylist")
	public ModelAndView addToPlaylist(@RequestParam(name = "songId") Integer songId,
									  @RequestParam(name = "playlistId") Integer playlistId) {
		ModelAndView mav = new ModelAndView();
		Songs songToAdd;
		Playlist userPlaylist;
		
		userPlaylist = playlistService.findPlaylistById(playlistId).get();
		songToAdd = songsService.findSongById(songId);
		
		if (!(userPlaylist.getSongs().contains(songToAdd)))
			playlistService.addSongToPlaylist(userPlaylist, songToAdd);
		else {
			System.out.println("Cannot add duplicate songs to a playlist");
			mav.addObject("error", "Error: Cannot Add Duplicates! Please choose another option.");
			mav.setViewName("/playlists/error");
			return mav;
		}
		
		mav.setViewName("/playlists/addtoplaylist");
		return new ModelAndView("redirect:/playlists/addtoplaylist");
	}
	
	@PostMapping("updateplaylistname")
	public ModelAndView updatePlaylistName(@RequestParam(name = "playlistId") Integer playlistId,
										   @RequestParam(name = "playlistName") String playlistName) {
		ModelAndView mav = new ModelAndView();
		String playlistNameCap = playlistName.substring(0, 1).toUpperCase() + playlistName.substring(1);
		Playlist playlistToRename = playlistService.findPlaylistById(playlistId).get();
		
		playlistToRename.setName(playlistNameCap);
		// Saves the playlist to DB
		playlistService.addPlaylist(playlistToRename);
		
		mav.setViewName("redirect:/myplaylists");
		return mav;
	}
	
	@PostMapping("deletesong")
	public ModelAndView removeSongFromPlaylist(@RequestParam(name = "songId") Integer songId,
			  								   @RequestParam(name = "playlistId") Integer playlistId) {
		ModelAndView mav = new ModelAndView();
		Songs songToAdd;
		Playlist userPlaylist;
		
		userPlaylist = playlistService.findPlaylistById(playlistId).get();
		songToAdd = songsService.findSongById(songId);
		playlistService.deleteSongFromPlaylist(userPlaylist, songToAdd);
		
		mav.setViewName("redirect:/myplaylists");
		return mav;
	}
	
	@GetMapping("/playlists/makeplaylist")
	public Model getMakePlaylist(Model model) {
		return model;
	}
	
	@RequestMapping(value = "/playlists/makeplaylist", method = RequestMethod.POST)
	public ModelAndView makePlaylist(@RequestParam(name="name") String playlistName,
									@RequestParam(name="description", defaultValue = "none") String playlistDesc) {
		// Capitalize first letter
		String playlistNameCap = playlistName.substring(0, 1).toUpperCase() + playlistName.substring(1);
		Playlist newPlaylist;
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User currentUser = userService.getUserByUsername(auth.getName());
		
		// Playlist description is optional
		if (playlistDesc != null && playlistDesc.length() > 0)
			newPlaylist = new Playlist(playlistNameCap, playlistDesc);
		else
			newPlaylist = new Playlist(playlistNameCap);
		
		playlistService.addPlaylist(newPlaylist);
		
		musicianService.addPlaylist(currentUser.getMusician().getId(), newPlaylist);
		
		return new ModelAndView("redirect:/myplaylists");
	}
	
}
