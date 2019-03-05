package com.novitatus.cqrs.todo.app.domain.task.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class CompleteTaskCommand
{
    @TargetAggregateIdentifier
    private String id;

    public CompleteTaskCommand()
    {
    }

    public CompleteTaskCommand(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
}