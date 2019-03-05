package com.novitatus.cqrs.todo.app.query.task;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import com.novitatus.cqrs.todo.app.domain.task.events.TaskCompletedEvent;
import com.novitatus.cqrs.todo.app.domain.task.events.TaskCreatedEvent;
import com.novitatus.cqrs.todo.app.domain.task.events.TaskStarredEvent;
import com.novitatus.cqrs.todo.app.domain.task.events.TaskTitleModifiedEvent;
import com.novitatus.cqrs.todo.app.domain.task.events.TaskUnstarredEvent;


@Component
public class TaskEntryUpdatingEventHandler
{
    private final TaskEntryRepository taskEntryRepository;

    public TaskEntryUpdatingEventHandler(TaskEntryRepository taskEntryRepository)
    {
        this.taskEntryRepository = taskEntryRepository;
    }

    @EventHandler
    void on(TaskCreatedEvent event)
    {
        TaskEntry task = new TaskEntry(event.getId(), event.getUsername(), event.getTitle(), false, false);

        taskEntryRepository.save(task);
    }

    @EventHandler
    void on(TaskCompletedEvent event)
    {
        TaskEntry task = taskEntryRepository.findById(event.getId()).get();

        task.setCompleted(true);

        taskEntryRepository.save(task);
    }

    @EventHandler
    void on(TaskTitleModifiedEvent event)
    {
        TaskEntry task = taskEntryRepository.findById(event.getId()).get();

        task.setTitle(event.getTitle());

        taskEntryRepository.save(task);
    }

    @EventHandler
    void on(TaskStarredEvent event)
    {
        TaskEntry task = taskEntryRepository.findById(event.getId()).get();

        task.setStarred(true);

        taskEntryRepository.save(task);
    }

    @EventHandler
    void on(TaskUnstarredEvent event)
    {
        TaskEntry task = taskEntryRepository.findById(event.getId()).get();

        task.setStarred(false);

        taskEntryRepository.save(task);
    }
}
