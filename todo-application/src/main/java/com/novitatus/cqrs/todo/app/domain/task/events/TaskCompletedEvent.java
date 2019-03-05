package com.novitatus.cqrs.todo.app.domain.task.events;

public class TaskCompletedEvent implements TaskEvent
{
    private final String id;

    public TaskCompletedEvent(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
}
