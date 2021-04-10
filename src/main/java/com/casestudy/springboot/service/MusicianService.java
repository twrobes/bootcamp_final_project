package com.casestudy.springboot.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casestudy.springboot.DAO.MusicianRepository;
import com.casestudy.springboot.entities.Musician;
import com.casestudy.springboot.entities.Playlist;


@Service
public class MusicianService {
	
	private final MusicianRepository musicianRepository;
	
	@Autowired
	public MusicianService(MusicianRepository musicianRepository) {
		this.musicianRepository = musicianRepository;
	}
	
	public List<Musician> getMusicians() {
		return musicianRepository.findAll();
	}
	
	public void deleteMusician(Integer musicianId) {
		boolean exists = musicianRepository.existsById(musicianId);
		
		if (!exists) {
			throw new IllegalStateException("Musician with id " + musicianId + " does not exist");
		}
		
		musicianRepository.deleteById(musicianId);
	}
	
	public void addNewMusician(String fName, String lName, Integer age) {
		Musician musician;
		boolean validfName = false;
		boolean validlName = false;
		boolean validAge = false;
		
		if (fName != null && fName.length() > 0) {
			validfName = true;
		}
		if (lName != null && lName.length() > 0) {
			validlName = true;
		}
		if (age != null && age > 0) {
			validAge = true;
		}
			
		if (validfName && validlName && validAge) {
			musician = new Musician(fName, lName, age);
			musicianRepository.save(musician);
		}
		else {
			System.out.println("Something went wrong!");
		}
	}

	public void updateMusician(Integer musicianId, String fName, String lName, Integer age) {
		Musician musician = musicianRepository.findById(musicianId)
				.orElseThrow(() -> new IllegalStateException("Musician with id " + musicianId + " does not exist"));
		
		if (fName != null && fName.length() > 0 && !Objects.equals(musician.getfName(), fName)) {
			musician.setfName(fName);
		}
		if (lName != null && lName.length() > 0 && !Objects.equals(musician.getlName(), lName)) {
			musician.setlName(lName);
		}
		if (age != null && age > 0 && !Objects.equals(musician.getAge(), age)) {
			musician.setAge(age);
		}
	}
	
	public List<Playlist> getMusicianPlaylists(Musician musician) {
		if (musician != null) {
			return musician.getPlaylists();
		}
		System.out.println("This musician has no playlists!");
		
		return null;
	}
	
	public boolean addPlaylist(Integer musicianId, Playlist playlist) {
		Optional<Musician> musician = musicianRepository.findById(musicianId);
		
		if (musician.isPresent()) {
			List<Playlist> allPlaylists = musician.get().getPlaylists();
			allPlaylists.add(playlist);
			musician.get().setPlaylist(allPlaylists);
			musicianRepository.save(musician.get());
			return true;
		}
		else
			return false;
	}
	
	
}
