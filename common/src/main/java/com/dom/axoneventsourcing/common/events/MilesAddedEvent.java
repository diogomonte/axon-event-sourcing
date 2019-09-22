package com.dom.axoneventsourcing.common.events;

import com.dom.axoneventsourcing.common.dto.Sport;

public class MilesAddedEvent extends BaseEvent<String> {

	public double miles;
	public Sport sport;

	public MilesAddedEvent() {
	}

	public MilesAddedEvent(String id, double miles, Sport sport) {
		super(id);
		this.miles = miles;
		this.sport = sport;
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

	@Override
	public String toString() {
		return "[id: " + id + " miles: " + miles + " sport: " + sport + "]";
	}
}
