package com.dom.axoneventsource.sportservice.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.jpa.JpaEventStorageEngine;
import org.axonframework.extensions.mongo.DefaultMongoTemplate;
import org.axonframework.extensions.mongo.eventsourcing.eventstore.MongoEventStorageEngine;
import org.axonframework.kafka.eventhandling.producer.DefaultProducerFactory;
import org.axonframework.kafka.eventhandling.producer.ProducerFactory;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.json.JacksonSerializer;
import org.axonframework.serialization.upcasting.event.SingleEventUpcaster;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class AxonConfig {

	@Bean
	public ProducerFactory<Object, Object> kafkaPublisherConfiguration() {
		Map<String, Object> properties = new HashMap<>();
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		properties.put(ProducerConfig.CLIENT_ID_CONFIG, "avro-producer");
		properties.put(ProducerConfig.ACKS_CONFIG, "all");
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
		properties.put("schema.registry.url", "http://localhost:8081");
		return DefaultProducerFactory.builder(properties).build();
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

	public JpaEventStorageEngine eventStorageEngine(Serializer eventSerializer, Serializer snapshotSerializer,
													DataSource dataSource, SingleEventUpcaster singleEventUpcaster,
													EntityManagerProvider entityManagerProvider,
													TransactionManager transactionManager) throws SQLException {
		return JpaEventStorageEngine.builder()
				.eventSerializer(eventSerializer)
				.snapshotSerializer(snapshotSerializer)
				.dataSource(dataSource)
				.entityManagerProvider(entityManagerProvider)
				.transactionManager(transactionManager)
				.upcasterChain(singleEventUpcaster)
				.build();
	}
}
