package com.casestudy.springboot.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.casestudy.springboot.DAO.PlaylistRepository;
import com.casestudy.springboot.entities.Playlist;
import com.casestudy.springboot.service.PlaylistService;

@SpringBootTest
class PlaylistTests {
	
	Playlist testPlaylist;
	
	@Autowired
	PlaylistRepository playlistRepository;
	
	@Autowired
	PlaylistService playlistService;
	
	@BeforeEach
	void setUp() throws Exception {
		testPlaylist = new Playlist("test");
		playlistRepository.save(testPlaylist);
	}
	
	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	@Transactional
	void testDeletePlaylist() {
		assertTrue(playlistService.deletePlaylist(testPlaylist.getId()));
	}
	
	@Test
	@Transactional
	void testFindPlaylistById() {
		Playlist expected = this.testPlaylist;
		Playlist actual = playlistService.findPlaylistById(this.testPlaylist.getId()).get();
		assertEquals(expected, actual);
	}
	
	@Test
	@Transactional
	void testAddPlaylist() {
		Playlist expected = new Playlist("new");
		playlistService.addPlaylist(expected);
		assertEquals(playlistService.findPlaylistById(expected.getId()).get(), expected);
	}

}
