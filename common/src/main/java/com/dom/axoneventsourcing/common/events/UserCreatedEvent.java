package com.dom.axoneventsourcing.common.events;

public class UserCreatedEvent extends BaseEvent<String> {

	public String userName;

	public UserCreatedEvent() {
	}

	public UserCreatedEvent(String id, String userName) {
		super(id);
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
