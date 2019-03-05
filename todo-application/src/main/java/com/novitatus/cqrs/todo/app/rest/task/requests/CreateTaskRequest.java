package com.novitatus.cqrs.todo.app.rest.task.requests;

public class CreateTaskRequest
{
    private String title;

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }
}
