package com.casestudy.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casestudy.springboot.DAO.PlaylistRepository;
import com.casestudy.springboot.entities.Playlist;
import com.casestudy.springboot.entities.Songs;

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
	
	public boolean deletePlaylist(Integer playlistId) {
		boolean exists = playlistRepository.existsById(playlistId);
		
		if (!exists) {
			throw new IllegalStateException("Playlist with id " + playlistId + " does not exist");
		}
		playlistRepository.deleteById(playlistId);
		
		return true;
	}
	
	public Optional<Playlist> findPlaylistById(Integer playlistId)
	{
		return playlistRepository.findById(playlistId);
	}
	
	public void addSongToPlaylist(Playlist playlist, Songs song) {
		
		if (playlist != null) {
			List<Songs> allSongs = playlist.getSongs();
			allSongs.add(song);
			playlist.setSongs(allSongs);
		}
		
		playlistRepository.save(playlist);
	}
	
	public Integer getTotalPlaylists() {
		return playlistRepository.findAll().size();
	}
	
	public void addPlaylist(Playlist playlist) {
		playlistRepository.save(playlist);
	}

	public void deleteSongFromPlaylist(Playlist playlist, Songs song) {
		List<Songs> allSongs = playlist.getSongs();
		allSongs.remove(song);
		playlist.setSongs(allSongs);
		
		playlistRepository.save(playlist);
	}
}
