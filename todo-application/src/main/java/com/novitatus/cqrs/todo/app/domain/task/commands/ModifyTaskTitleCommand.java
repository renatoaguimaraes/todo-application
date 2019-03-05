package com.novitatus.cqrs.todo.app.domain.task.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class ModifyTaskTitleCommand
{
    @TargetAggregateIdentifier
    private String id;
    private String title;

    public ModifyTaskTitleCommand(String id, String title)
    {
        this.id = id;
        this.title = title;
    }

    public String getId()
    {
        return id;
    }

    public String getTitle()
    {
        return title;
    }
}
