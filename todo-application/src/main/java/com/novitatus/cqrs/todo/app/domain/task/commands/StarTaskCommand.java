package com.novitatus.cqrs.todo.app.domain.task.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class StarTaskCommand
{
    @TargetAggregateIdentifier
    private String id;

    public StarTaskCommand()
    {
    }

    public StarTaskCommand(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    
    public void setId(String id)
    {
        this.id = id;
    }
}
