package com.dom.axoneventsourcing.common.events;

import com.dom.axoneventsourcing.common.dto.Sport;

public class BikeMilesAddedEvent extends MilesAddedEvent {

	public BikeMilesAddedEvent() {
	}

	public BikeMilesAddedEvent(String id, double miles) {
		super(id, miles, Sport.BIKE);
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
