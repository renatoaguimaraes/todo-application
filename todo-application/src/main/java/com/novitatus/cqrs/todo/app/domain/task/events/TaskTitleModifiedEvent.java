package com.novitatus.cqrs.todo.app.domain.task.events;

public class TaskTitleModifiedEvent implements TaskEvent
{
    private String id;
    private String title;

    public TaskTitleModifiedEvent()
    {
    }

    public TaskTitleModifiedEvent(String id, String title)
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
