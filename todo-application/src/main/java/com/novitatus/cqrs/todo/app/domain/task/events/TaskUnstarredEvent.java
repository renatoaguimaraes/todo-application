package com.novitatus.cqrs.todo.app.domain.task.events;

public class TaskUnstarredEvent implements TaskEvent
{
    private String id;

    public TaskUnstarredEvent()
    {
    }

    public TaskUnstarredEvent(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
}
