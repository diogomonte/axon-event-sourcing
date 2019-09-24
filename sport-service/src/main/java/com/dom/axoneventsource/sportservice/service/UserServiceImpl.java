package com.dom.axoneventsource.sportservice.service;

import com.dom.axoneventsource.sportservice.aggregate.UserAggregate;
import com.dom.axoneventsource.sportservice.api.MilesRequest;
import com.dom.axoneventsource.sportservice.api.UserRequest;
import com.dom.axoneventsource.sportservice.command.AddMilesCommand;
import com.dom.axoneventsource.sportservice.command.CreateUserCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	private final CommandGateway commandGateway;

	public UserServiceImpl(CommandGateway commandGateway) {
		this.commandGateway = commandGateway;
	}

	@Override
	public CompletableFuture<String> createUser(UserRequest userRequest) {

		return commandGateway.send(new CreateUserCommand(UUID.randomUUID().toString(), userRequest.getUserName()));
	}

	@Override
	public CompletableFuture<String> addMiles(String userId, MilesRequest sportServiceRequest) {
		log.info("Sending command add miles: {}", sportServiceRequest.toString());
		return commandGateway.send(new AddMilesCommand(userId, sportServiceRequest.getMiles(), sportServiceRequest.getSport()));
	}

}
