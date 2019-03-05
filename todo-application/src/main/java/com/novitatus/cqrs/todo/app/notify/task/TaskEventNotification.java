package com.novitatus.cqrs.todo.app.notify.task;

import com.novitatus.cqrs.todo.app.domain.task.events.TaskEvent;

public class TaskEventNotification
{
    private String type;
    private TaskEvent data;

    public TaskEventNotification()
    {
    }

    public TaskEventNotification(String type, TaskEvent data)
    {
        this.type = type;
        this.data = data;
    }

    public String getType()
    {
        return type;
    }

    public TaskEvent getData()
    {
        return data;
    }

}
