package com.casestudy.springboot.service;

import java.util.List;

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
	
	public void deleteSong(Integer songsId) {
		boolean exists = songsRepository.existsById(songsId);
		
		if (!exists) {
			throw new IllegalStateException("Songs with id " + songsId + " does not exist");
		}
		
		songsRepository.deleteById(songsId);
	}
}
