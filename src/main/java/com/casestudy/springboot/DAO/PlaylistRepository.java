package com.casestudy.springboot.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casestudy.springboot.entities.Playlist;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Integer> {

}
