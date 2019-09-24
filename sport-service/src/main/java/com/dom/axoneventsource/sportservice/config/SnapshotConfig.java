package com.dom.axoneventsource.sportservice.config;

import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.eventsourcing.EventCountSnapshotTriggerDefinition;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.spring.eventsourcing.SpringAggregateSnapshotter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;

@Configuration
public class SnapshotConfig {

	@Bean
	public EventCountSnapshotTriggerDefinition allEventsSnapshot(SpringAggregateSnapshotter snapshotter) {
		return  new EventCountSnapshotTriggerDefinition(snapshotter, 3);
	}

	@Bean
	public SpringAggregateSnapshotter snapshotter(EventStore eventStore, TransactionManager transactionManager) {
		return SpringAggregateSnapshotter.builder()
				.eventStore(eventStore)
				.transactionManager(transactionManager)
				.executor(Executors.newSingleThreadExecutor())
				.build();
	}
}
