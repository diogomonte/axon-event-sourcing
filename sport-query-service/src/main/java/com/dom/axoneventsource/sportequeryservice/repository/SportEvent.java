package com.dom.axoneventsource.sportequeryservice.repository;

import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

public class SportEvent {

	@Field
	private double miles;
	@Field
	private Date createdDate;

	public SportEvent(double miles) {
		this.miles = miles;
		this.createdDate = new Date();
	}

	public double getMiles() {
		return miles;
	}

	public Date getCreatedDate() {
		return createdDate;
	}
}
