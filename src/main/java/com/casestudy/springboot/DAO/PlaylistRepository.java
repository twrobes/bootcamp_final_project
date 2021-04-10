package com.casestudy.springboot.DAO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.casestudy.springboot.entities.Playlist;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Integer> {
	
}
