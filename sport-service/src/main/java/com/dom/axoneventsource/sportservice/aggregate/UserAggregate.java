package com.dom.axoneventsource.sportservice.aggregate;

import com.dom.axoneventsource.sportservice.command.AddMilesCommand;
import com.dom.axoneventsource.sportservice.command.CreateUserCommand;
import com.dom.axoneventsourcing.common.events.BikeMilesAddedEvent;
import com.dom.axoneventsourcing.common.events.MilesAddedEvent;
import com.dom.axoneventsourcing.common.events.RunMilesAddedEvent;
import com.dom.axoneventsourcing.common.events.SwimMilesAddedEvent;
import com.dom.axoneventsourcing.common.events.UserCreatedEvent;
import lombok.Data;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aggregate
@Data
public class UserAggregate {

	private static final Logger log = LoggerFactory.getLogger(UserAggregate.class);

	@AggregateIdentifier
	private String id;
	private String userName;
	private double bikeMiles;
	private double runMiles;
	private double swimMiles;

	public UserAggregate() {}

	@CommandHandler
	public UserAggregate(CreateUserCommand createUserCommand) {
		AggregateLifecycle.apply(new UserCreatedEvent(createUserCommand.id, createUserCommand.userName));
	}

	@EventSourcingHandler
	protected void on(UserCreatedEvent userCreatedEvent) {
		this.id = userCreatedEvent.id;
		this.userName = userCreatedEvent.userName;
	}

	@CommandHandler
	public void on(AddMilesCommand addMilesCommand) {
		switch (addMilesCommand.sport) {
			case RUN:
				AggregateLifecycle.apply(new RunMilesAddedEvent(addMilesCommand.id, addMilesCommand.miles));
				return;
			case BIKE:
				AggregateLifecycle.apply(new BikeMilesAddedEvent(addMilesCommand.id, addMilesCommand.miles));
				return;
			case SWIM:
				AggregateLifecycle.apply(new SwimMilesAddedEvent(addMilesCommand.id, addMilesCommand.miles));
				return;
			default:
				throw new IllegalArgumentException("unknown sport type");
		}
	}

	@EventSourcingHandler
	protected void on(BikeMilesAddedEvent milesAddedEvent) {
		this.bikeMiles += milesAddedEvent.miles;
		log.info("Bike miles added. Total miles: " + bikeMiles);
	}

	@EventSourcingHandler
	protected void on(RunMilesAddedEvent milesAddedEvent) {
		this.runMiles += milesAddedEvent.miles;
		log.info("Run miles added. Total miles: " + runMiles);
	}

	@EventSourcingHandler
	protected void on(SwimMilesAddedEvent milesAddedEvent) {
		this.swimMiles += milesAddedEvent.miles;
		log.info("Swim miles added. Total miles: " + swimMiles);
	}


}
