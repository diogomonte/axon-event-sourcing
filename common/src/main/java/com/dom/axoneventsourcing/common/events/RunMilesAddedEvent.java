package com.dom.axoneventsourcing.common.events;

import com.dom.axoneventsourcing.common.dto.Sport;

public class RunMilesAddedEvent extends MilesAddedEvent {

	public RunMilesAddedEvent() {
	}

	public RunMilesAddedEvent(String id, double miles) {
		super(id, miles, Sport.RUN);
	}

	public double getMiles() {
		return miles;
	}

	public void setMiles(double miles) {
		this.miles = miles;
	}

	public Sport getSport() {
		return sport;
	}

	public void setSport(Sport sport) {
		this.sport = sport;
	}
}
