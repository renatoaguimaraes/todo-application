package com.novitatus.cqrs.todo.app.domain.task.exceptions;

public class TaskAlreadyUnstarredException extends RuntimeException
{
    public TaskAlreadyUnstarredException(String message)
    {
        super(message);
    }
}
