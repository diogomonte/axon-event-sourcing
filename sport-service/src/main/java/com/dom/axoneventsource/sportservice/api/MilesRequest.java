package com.dom.axoneventsource.sportservice.api;

import com.dom.axoneventsourcing.common.dto.Sport;
import lombok.ToString;

public class MilesRequest {

	private double miles;
	private Sport sport;

	public MilesRequest() {

	}

	public Sport getSport() {
		return sport;
	}

	public void setSport(Sport sport) {
		this.sport = sport;
	}

	public double getMiles() {
		return miles;
	}

	public void setMiles(double miles) {
		this.miles = miles;
	}

	@Override
	public String toString() {
		return "[miles: " + miles +  " sport: " + sport + "]";
	}
}
