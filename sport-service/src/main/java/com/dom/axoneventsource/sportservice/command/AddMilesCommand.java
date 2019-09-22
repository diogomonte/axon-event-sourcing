package com.dom.axoneventsource.sportservice.command;

import com.dom.axoneventsourcing.common.dto.Sport;

public class AddMilesCommand extends BaseCommand<String> {

	public double miles;
	public Sport sport;

	public AddMilesCommand(String id, double miles, Sport sport) {
		super(id);
		this.miles = miles;
		this.sport = sport;
	}
}
