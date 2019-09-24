package com.dom.axoneventsource.sportequeryservice.service;

import com.dom.axoneventsource.sportequeryservice.repository.SportEvent;
import com.dom.axoneventsource.sportequeryservice.repository.User;
import com.dom.axoneventsource.sportequeryservice.repository.UserRepository;
import com.dom.axoneventsourcing.common.dto.Sport;
import com.dom.axoneventsourcing.common.events.MilesAddedEvent;
import com.dom.axoneventsourcing.common.events.UserCreatedEvent;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Service
@ProcessingGroup("sports")
public class SportQueryService {

	private static final Logger log = LoggerFactory.getLogger(SportQueryService.class);

	@Autowired
	private UserRepository userRepository;

	@EventHandler
	public void on(UserCreatedEvent userCreatedEvent) {
		userRepository.findById(userCreatedEvent.id)
				.ifPresentOrElse(u -> log.info("User id {} already saved", userCreatedEvent.id),
						() -> userRepository.save(new User(userCreatedEvent.id, userCreatedEvent.userName)));
	}

	@EventHandler
	public void on(MilesAddedEvent event) {
		log.info("Sport event received: " + event.toString());
		userRepository.findById(event.id)
				.ifPresentOrElse(user -> saveUserEvent.accept(user, event),
						() -> log.info("User id: {} not found", event.id));
	}

	private BiConsumer<User, MilesAddedEvent> saveUserEvent = (user, milesEvent) -> {
		var sports = user.getEvents(milesEvent.sport);
		sports.add(new SportEvent(milesEvent.miles));

		user.getSportEvents().put(milesEvent.sport, sports);
		userRepository.save(user);
	};
}
