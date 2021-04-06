package com.casestudy.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casestudy.springboot.DAO.PlaylistRepository;
import com.casestudy.springboot.entities.Playlist;

@Service
public class PlaylistService {
private final PlaylistRepository playlistRepository;
	
	@Autowired
	public PlaylistService(PlaylistRepository playlistRepository) {
		this.playlistRepository = playlistRepository;
	}
	
	public List<Playlist> getPlaylists() {
		return playlistRepository.findAll();
	}
	
	public void deletePlaylist(Integer playlistId) {
		boolean exists = playlistRepository.existsById(playlistId);
		
		if (!exists) {
			throw new IllegalStateException("Playlist with id " + playlistId + " does not exist");
		}
		
		playlistRepository.deleteById(playlistId);
	}
}
