package com.dom.axoneventsource.sportequeryservice.controller;

import com.dom.axoneventsource.sportequeryservice.repository.User;
import com.dom.axoneventsource.sportequeryservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserSearchController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping
	public ResponseEntity<List<User>> findAllUsers() {
		return ResponseEntity.ok(userRepository.findAll());
	}
}
