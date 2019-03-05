package com.novitatus.cqrs.todo.app.configuration;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleEventBus;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfiguration
{
    @Bean
    public EventStore getEventStore(EventStorageEngine engine)
    {
        return EmbeddedEventStore.builder().storageEngine(engine).build();
    }

    @Bean
    public EventStorageEngine getStorageEngine()
    {
        return new InMemoryEventStorageEngine();
    }

    @Bean
    public CommandBus getCommandBus()
    {
        return SimpleCommandBus.builder().build();
    }

    @Bean
    public EventBus getEventBus()
    {
        return SimpleEventBus.builder().build();
    }
}
