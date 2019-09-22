package com.dom.axoneventsource.sportservice.service;

import com.dom.axoneventsource.sportservice.api.MilesRequest;
import com.dom.axoneventsource.sportservice.api.UserRequest;

import java.util.concurrent.CompletableFuture;

public interface UserService {

	CompletableFuture<String> createUser(UserRequest userRequest);
	CompletableFuture<String> addMiles(String userId, MilesRequest sportServiceRequest);
}
