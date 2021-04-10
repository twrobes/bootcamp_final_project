package com.casestudy.springboot.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.casestudy.springboot.entities.Songs;

@Repository
public interface SongsRepository extends JpaRepository<Songs, Integer> {
	@Query("SELECT s FROM Songs s WHERE s.title = :title")
	Songs findSongByTitle(@Param("title") String title);
}

