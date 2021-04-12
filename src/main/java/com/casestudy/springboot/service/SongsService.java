package com.casestudy.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casestudy.springboot.DAO.SongsRepository;
import com.casestudy.springboot.entities.Songs;

@Service
public class SongsService {

private final SongsRepository songsRepository;
	
	@Autowired
	public SongsService(SongsRepository songsRepository) {
		this.songsRepository = songsRepository;
	}
	
	public List<Songs> getSongs() {
		return songsRepository.findAll();
	}
	
	public void addSong(Songs song) {
		songsRepository.save(song);
	}
	
	public Songs findSongById(Integer songId) {
		Optional<Songs> song = songsRepository.findById(songId);
		return song.get();
	}
	
	public Integer getTotalSongs() {
		return songsRepository.findAll().size();
	}
	
	public void deleteSong(Integer songsId) {
		boolean exists = songsRepository.existsById(songsId);
		
		if (!exists) {
			throw new IllegalStateException("Songs with id " + songsId + " does not exist");
		}
		
		songsRepository.deleteById(songsId);
	}
}
