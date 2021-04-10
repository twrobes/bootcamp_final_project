package com.casestudy.springboot.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casestudy.springboot.DAO.UserRepository;
import com.casestudy.springboot.entities.User;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	private Optional<User> userOptional;
	private User user;
	
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public List<User> getUsers() {
		return userRepository.findAll();
	}
	
	public void setCurrentUser(Integer id) {
		this.userOptional = userRepository.findById(id);
		
		if (userOptional.isPresent()) {
			System.out.println("User session successful");
			this.user = userOptional.get();
			System.out.println(this.user.getEmail());
			System.out.println("done with setCurrentUser");
		}
		else {
			System.out.println("User not found");
		}
	}
	
	public User getCurrentUser() {
		System.out.println(user);
		return this.user;
	}
	
	public User getUserByEmail(String email) {
		Optional<User> user = userRepository.findUserByEmail(email);
		
		if (user.isPresent()) {
			return user.get();
		}
		else {
			System.out.println("No user with this email " + email);
			return null;
		}
	}
	
	public User getUserByUsername(String username) {
		User user = userRepository.findUserByUsername(username);
		
		if (!(user == null)) {
			return user;
		}
		System.out.println("Did not find a user with that username");
		
		return null;
	}

	public void addNewUser(User user) {
		Optional<User> userEmail = userRepository.findUserByEmail(user.getEmail());
		
		if (userEmail.isPresent()) {
			throw new IllegalStateException("This email already has an account!");
		}
		
		userRepository.save(user);
	}

	public void deleteUser(Integer userId) {
		boolean exists = userRepository.existsById(userId);
		
		if (!exists) {
			throw new IllegalStateException("User with id " + userId + " does not exist");
		}
		
		userRepository.deleteById(userId);
	}
	
	@Transactional
	public void updateUser(Integer userId, String email, String password) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new IllegalStateException("User with id " + userId + " does not exist"));
		
		if (email != null && email.length() > 0 && !Objects.equals(user.getEmail(), email)) {
			Optional<User> userOptional = userRepository.findUserByEmail(email);
			
			if (userOptional.isPresent()) {
				throw new IllegalStateException("This email has already been registered");
			}
			user.setEmail(email);
		}
		
		if (password != null && password.length() > 0 && !Objects.equals(user.getPassword(), password)) {
			user.setPassword(password);
		}
		
	}
	
	public boolean userIsValid(String email, String password) {
		Optional<User> user = userRepository.findUserByEmail(email);
		
		if (user.isPresent())
			if (user.get().getPassword().equals(password))
				return true;
		System.out.println("The username and/or password is incorrect");
		return false;
	}
}
