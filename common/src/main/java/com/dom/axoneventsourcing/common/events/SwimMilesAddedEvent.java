package com.dom.axoneventsourcing.common.events;

import com.dom.axoneventsourcing.common.dto.Sport;

public class SwimMilesAddedEvent extends MilesAddedEvent {

	public SwimMilesAddedEvent() {
	}

	public SwimMilesAddedEvent(String id, double miles) {
		super(id, miles, Sport.SWIM);
	}
}
