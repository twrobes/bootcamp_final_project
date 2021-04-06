package com.casestudy.springboot.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casestudy.springboot.entities.Songs;

@Repository
public interface SongsRepository extends JpaRepository<Songs, Integer>{

}
