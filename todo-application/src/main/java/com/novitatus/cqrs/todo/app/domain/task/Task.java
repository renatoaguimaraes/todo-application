package com.novitatus.cqrs.todo.app.domain.task;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import com.novitatus.cqrs.todo.app.domain.task.commands.CompleteTaskCommand;
import com.novitatus.cqrs.todo.app.domain.task.commands.CreateTaskCommand;
import com.novitatus.cqrs.todo.app.domain.task.commands.ModifyTaskTitleCommand;
import com.novitatus.cqrs.todo.app.domain.task.commands.StarTaskCommand;
import com.novitatus.cqrs.todo.app.domain.task.commands.UnstarTaskCommand;
import com.novitatus.cqrs.todo.app.domain.task.events.TaskCompletedEvent;
import com.novitatus.cqrs.todo.app.domain.task.events.TaskCreatedEvent;
import com.novitatus.cqrs.todo.app.domain.task.events.TaskStarredEvent;
import com.novitatus.cqrs.todo.app.domain.task.events.TaskTitleModifiedEvent;
import com.novitatus.cqrs.todo.app.domain.task.events.TaskUnstarredEvent;
import com.novitatus.cqrs.todo.app.domain.task.exceptions.TaskAlreadyCompletedException;
import com.novitatus.cqrs.todo.app.domain.task.exceptions.TaskAlreadyStarredException;
import com.novitatus.cqrs.todo.app.domain.task.exceptions.TaskAlreadyUnstarredException;

@Aggregate
public class Task
{
    @AggregateIdentifier
    private String id;
    private boolean completed;
    private boolean starred;

    public Task()
    {
    }

    @CommandHandler
    public Task(CreateTaskCommand command)
    {
        apply(new TaskCreatedEvent(command.getId(), command.getUsername(), command.getTitle()));
    }

    @CommandHandler
    void on(CompleteTaskCommand command)
    {
        apply(new TaskCompletedEvent(command.getId()));
    }

    @CommandHandler
    void on(StarTaskCommand command)
    {
        if (starred)
        {
            throw new TaskAlreadyStarredException("Task [ identifier = " + id + " ] is starred.");
        }

        apply(new TaskStarredEvent(command.getId()));
    }

    @CommandHandler
    void on(UnstarTaskCommand command)
    {
        if (!starred)
        {
            throw new TaskAlreadyUnstarredException("Task [ identifier = " + id + " ] is unstarred.");
        }

        apply(new TaskUnstarredEvent(command.getId()));
    }

    @CommandHandler
    void on(ModifyTaskTitleCommand command)
    {
        if (completed)
        {
            throw new TaskAlreadyCompletedException("Task [ identifier = " + id + " ] is completed.");
        }

        apply(new TaskTitleModifiedEvent(command.getId(), command.getTitle()));
    }

    @EventSourcingHandler
    void on(TaskCreatedEvent event)
    {
        this.id = event.getId();
    }

    @EventSourcingHandler
    void on(TaskStarredEvent event)
    {
        this.starred = true;
    }

    @EventSourcingHandler
    void on(TaskUnstarredEvent event)
    {
        this.starred = false;
    }

    @EventSourcingHandler
    void on(TaskCompletedEvent event)
    {
        this.completed = true;
    }
}
