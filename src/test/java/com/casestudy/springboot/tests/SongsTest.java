package com.casestudy.springboot.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.casestudy.springboot.DAO.SongsRepository;
import com.casestudy.springboot.entities.Songs;
import com.casestudy.springboot.service.SongsService;

@SpringBootTest
class SongsTest {

	Songs testSong;
	
	@Autowired
	SongsRepository songsRepository;
	
	@Autowired
	SongsService songsService;
	
	@BeforeEach
	void setUp() throws Exception {
		testSong = new Songs("testArtist", "testTitle", "testScore");
		songsRepository.save(testSong);
	}
	
	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	@Transactional
	void testFindSongById() {
		Songs actual = songsService.findSongById(testSong.getId());
		assertEquals(testSong, actual);
	}
	
	@Test
	@Transactional
	void testDeleteSong() {
		songsService.deleteSong(testSong.getId());
		assertTrue(songsRepository.findById(testSong.getId()).isEmpty());
	}

}
