package com.novitatus.cqrs.todo.app.query.task;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TaskEntry
{
    @Id
    private String id;
    private String username;
    private String title;
    private boolean completed;
    private boolean starred;

    public TaskEntry()
    {
    }

    public TaskEntry(String id, String username, String title, boolean completed, boolean starred)
    {
        this.id = id;
        this.username = username;
        this.title = title;
        this.completed = completed;
        this.starred = starred;
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

    public boolean isCompleted()
    {
        return completed;
    }

    public boolean isStarred()
    {
        return starred;
    }

    public void setCompleted(boolean completed)
    {
        this.completed = completed;
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

    public void setStarred(boolean starred)
    {
        this.starred = starred;
    }

}