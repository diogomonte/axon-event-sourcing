package com.dom.axoneventsource.sportservice.command;

public class CreateUserCommand extends BaseCommand<String> {

	public String userName;

	public CreateUserCommand(String id, String userName) {
		super(id);
		this.userName = userName;
	}
}
