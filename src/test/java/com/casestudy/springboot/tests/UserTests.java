package com.casestudy.springboot.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.casestudy.springboot.DAO.UserRepository;
import com.casestudy.springboot.entities.User;
import com.casestudy.springboot.service.UserService;

@SpringBootTest
class UserTests {

	User testUser;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
	@BeforeEach
	void setUp() throws Exception {
		testUser = new User("test", "test@test.com", "testpass");
		testUser.setRoles("USER");
		userRepository.save(testUser);
	}
	
	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	@Transactional
	void testUpdateUser() {
		User expected = new User("test", "updated@updated.com", "updated");
		expected.setId(testUser.getId());
		expected.setRoles("USER");
		userService.updateUser(testUser.getId(), "updated@updated.com", "updated");
		assertEquals(expected, testUser);
	}
	
	@Test
	@Transactional
	void testUserIsValid() {
		boolean expected = userService.userIsValid("test@test.com", "testpass");
		assertTrue(expected);
	}
	
	@Test
	@Transactional
	void testAddNewUser() {
		User newUser = new User("new", "new@user.com", "newpass");
		newUser.setRoles("USER");
		userService.addNewUser(newUser);
		User expected = userRepository.findUserByUsername("new");
		assertEquals(expected, newUser);
	}
	
	@Test
	@Transactional
	void testGetUserByUsername() {
		User actual = userRepository.findUserByEmail("test@test.com").get();
		assertEquals(testUser, actual);
	}
	
	@Test
	@Transactional
	void testGetUserByEmail() {
		User actual = userRepository.findUserByEmail("test@test.com").get();
		assertEquals(testUser, actual);
	}

}
