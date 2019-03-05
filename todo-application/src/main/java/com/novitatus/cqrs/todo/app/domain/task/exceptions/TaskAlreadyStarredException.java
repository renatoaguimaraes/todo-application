package com.novitatus.cqrs.todo.app.domain.task.exceptions;

public class TaskAlreadyStarredException extends RuntimeException
{
    public TaskAlreadyStarredException(String message)
    {
        super(message);
    }
}
