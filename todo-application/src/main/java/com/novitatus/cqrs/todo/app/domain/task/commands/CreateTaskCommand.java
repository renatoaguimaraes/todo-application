package com.novitatus.cqrs.todo.app.domain.task.commands;

public class CreateTaskCommand
{
    private String id;
    private String username;
    private String title;

    public CreateTaskCommand()
    {
    }

    public CreateTaskCommand(String id, String username, String title)
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

    public void setId(String id)
    {
        this.id = id;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

}
