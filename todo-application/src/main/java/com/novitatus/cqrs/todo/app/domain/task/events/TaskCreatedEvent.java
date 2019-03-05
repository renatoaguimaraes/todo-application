package com.novitatus.cqrs.todo.app.domain.task.events;

public class TaskCreatedEvent implements TaskEvent
{
    private String id;
    private String username;
    private String title;

    public TaskCreatedEvent()
    {
    }

    public TaskCreatedEvent(String id, String username, String title)
    {
        this.id = id;
        this.username = username;
        this.title = title;
    }

    public String getId()
    {
        return id;
    }

    public String getUsername()
    {
        return username;
    }

    public String getTitle()
    {
        return title;
    }

}
