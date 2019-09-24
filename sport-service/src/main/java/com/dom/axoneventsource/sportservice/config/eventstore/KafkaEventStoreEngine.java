package com.dom.axoneventsource.sportservice.config.eventstore;

import org.axonframework.eventhandling.DomainEventData;
import org.axonframework.eventhandling.DomainEventMessage;
import org.axonframework.eventhandling.EventMessage;
import org.axonframework.eventhandling.TrackedEventData;
import org.axonframework.eventhandling.TrackingToken;
import org.axonframework.eventsourcing.eventstore.AbstractEventStorageEngine;
import org.axonframework.serialization.Serializer;

import java.util.List;
import java.util.stream.Stream;

public class KafkaEventStoreEngine extends AbstractEventStorageEngine {

	/**
	 * Instantiate a {@link AbstractEventStorageEngine} based on the fields contained in the {@link Builder}.
	 *
	 * @param builder the {@link Builder} used to instantiate a {@link AbstractEventStorageEngine} instance
	 */
	protected KafkaEventStoreEngine(Builder builder) {
		super(builder);
	}

	@Override
	protected void appendEvents(List<? extends EventMessage<?>> events, Serializer serializer) {
	}

	@Override
	protected void storeSnapshot(DomainEventMessage<?> snapshot, Serializer serializer) {

	}

	@Override
	protected Stream<? extends DomainEventData<?>> readEventData(String identifier, long firstSequenceNumber) {
		return null;
	}

	@Override
	protected Stream<? extends TrackedEventData<?>> readEventData(TrackingToken trackingToken, boolean b) {
		return null;
	}

	@Override
	protected Stream<? extends DomainEventData<?>> readSnapshotData(String aggregateIdentifier) {
		return null;
	}
}
