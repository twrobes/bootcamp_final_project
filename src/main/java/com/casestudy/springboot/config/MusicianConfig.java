//package com.casestudy.springboot.config;
//
//import java.util.List;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.casestudy.springboot.DAO.MusicianRepository;
//import com.casestudy.springboot.entities.Musician;
//
//@Configuration
//public class MusicianConfig {
//	
//	@Bean
//	CommandLineRunner commandLineRunner(MusicianRepository repository) {
//		return args -> {
//			Musician thomas = new Musician(
//					"Thomas",
//					"Wrobel",
//					24
//			);
//			
//			Musician test = new Musician(
//					"test",
//					"test",
//					69
//			);
//			
//			repository.saveAll(List.of(thomas, test));
//		};
//	}
//}
