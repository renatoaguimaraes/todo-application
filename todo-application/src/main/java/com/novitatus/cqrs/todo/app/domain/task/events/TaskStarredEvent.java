package com.novitatus.cqrs.todo.app.domain.task.events;

public class TaskStarredEvent implements TaskEvent
{
    private String id;

    public TaskStarredEvent()
    {
    }

    public TaskStarredEvent(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }

}
