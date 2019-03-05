package com.novitatus.cqrs.todo.app.domain.task.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class UnstarTaskCommand
{
    @TargetAggregateIdentifier
    private String id;

    public UnstarTaskCommand()
    {
    }

    public UnstarTaskCommand(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
}