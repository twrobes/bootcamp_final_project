package com.casestudy.springboot.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.casestudy.springboot.DAO.MusicianRepository;
import com.casestudy.springboot.entities.Musician;
import com.casestudy.springboot.entities.Playlist;
import com.casestudy.springboot.service.MusicianService;

@SpringBootTest
class MusicianTests {

	Musician testMusician;
	
	@Autowired
	MusicianRepository musicianRepository;
	
	@Autowired
	MusicianService musicianService;
	
	@BeforeEach
	void setUp() throws Exception {
		testMusician = new Musician("test", "musician", 50);
		musicianRepository.save(testMusician);
	}
	
	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	@Transactional
	void testGetMusicianPlaylists() {
		List<Playlist> expected = new ArrayList<>();
		assertEquals(expected, musicianService.getMusicianPlaylists(testMusician));
	}
	
	@Test
	@Transactional
	void testAddPlaylist() {
		Playlist expected = new Playlist("testPlaylist");
		boolean result = musicianService.addPlaylist(testMusician.getId(), expected);
		assertTrue(result);
	}
	
	@Test
	@Transactional
	void testSaveMusician() {
		Musician actual = musicianRepository.getOne(testMusician.getId());
		assertEquals(testMusician, actual);
	}
}
