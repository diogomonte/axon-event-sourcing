package com.dom.axoneventsource.sportequeryservice.repository;

import com.dom.axoneventsourcing.common.dto.Sport;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Document(value = "users")
public class User {

	@Id
	private String id;
	private String name;
	@Field
	private Map<Sport, List<SportEvent>> sportEvents;

	public User(String id, String name) {
		this.id = id;
		this.name = name;
		this.sportEvents = new HashMap<>();
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Map<Sport, List<SportEvent>> getSportEvents() {
		if (sportEvents == null) {
			return new HashMap<>();
		}
		return sportEvents;
	}

	public List<SportEvent> getEvents(Sport sport) {
		var events = getSportEvents().get(sport);
		if (events == null) {
			return new ArrayList<>();
		}
		return events;
	}

	public void setSportEvents(Map<Sport, List<SportEvent>> sportEvents) {
		this.sportEvents = sportEvents;
	}
}
