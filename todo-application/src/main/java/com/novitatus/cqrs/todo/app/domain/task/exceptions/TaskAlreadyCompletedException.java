package com.novitatus.cqrs.todo.app.domain.task.exceptions;

public class TaskAlreadyCompletedException extends RuntimeException
{
    public TaskAlreadyCompletedException(String message)
    {
        super(message);
    }
}
