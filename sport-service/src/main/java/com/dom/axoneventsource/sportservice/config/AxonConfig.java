package com.dom.axoneventsource.sportservice.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.eventsourcing.EventCountSnapshotTriggerDefinition;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.extensions.mongo.DefaultMongoTemplate;
import org.axonframework.extensions.mongo.eventsourcing.eventstore.MongoEventStorageEngine;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.json.JacksonSerializer;
import org.axonframework.spring.eventsourcing.SpringAggregateSnapshotter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.concurrent.Executors;

@Configuration
public class AxonConfig {

	@Bean
	public EventCountSnapshotTriggerDefinition accountAggregateSnapshotTriggerDefinition(SpringAggregateSnapshotter snapshotter) {
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

	@Primary
	@Bean
	public Serializer serializer() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
		return JacksonSerializer.builder().objectMapper(objectMapper).build();
	}

	@Bean
	public EventStorageEngine storageEngine(MongoClient mongoClient) {
		return MongoEventStorageEngine.builder()
				.mongoTemplate(DefaultMongoTemplate.builder().mongoDatabase(mongoClient).build()).build();
	}
}
