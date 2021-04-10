package com.casestudy.springboot.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.casestudy.springboot.entities.Musician;

@Repository
public interface MusicianRepository extends JpaRepository<Musician, Integer> {
	
//	@Query("SELECT m FROM Musician m WHERE m.id = :id")
//	Optional<Musician> findMusicianById(@Param("id") Integer id);
}
