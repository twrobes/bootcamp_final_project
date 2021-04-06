package com.casestudy.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.springboot.entities.User;
import com.casestudy.springboot.service.UserService;

@RestController
public class UserController {
private final UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	//@GetMapping("/user")
	//public List<User> getUsers() {
	//	return userService.getUsers();
	//}
	/*
	@PostMapping
	public void registerNewUser(@RequestBody User user) {
		userService.addNewUser(user);
	}
	
	@DeleteMapping(path = "{userId}")
	public void deleteUser(@PathVariable("userId") Integer userId) {
		userService.deleteUser(userId);
	}
	
	@PutMapping(path = "{userId}")
		public void updateUser(@PathVariable("userId") Integer userId,
				@RequestParam(required = true) String email,
				@RequestParam(required = true) String password) {
			userService.updateUser(userId, email, password);
	}*/
}
