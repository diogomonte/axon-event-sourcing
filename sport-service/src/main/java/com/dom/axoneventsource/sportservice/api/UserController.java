package com.dom.axoneventsource.sportservice.api;

import com.dom.axoneventsource.sportservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	public CompletableFuture<String> createUser(@RequestBody UserRequest userRequest) {
		return userService.createUser(userRequest);
	}

	@PostMapping("/{userId}/miles")
	public CompletableFuture<String> addBikeMiles(@PathVariable String userId, @RequestBody MilesRequest milesRequest) {
		return userService.addMiles(userId, milesRequest);
	}

}
