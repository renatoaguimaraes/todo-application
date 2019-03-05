package com.novitatus.cqrs.todo.app.notify.task;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

import com.novitatus.cqrs.todo.app.domain.task.events.TaskCompletedEvent;
import com.novitatus.cqrs.todo.app.domain.task.events.TaskCreatedEvent;
import com.novitatus.cqrs.todo.app.domain.task.events.TaskEvent;
import com.novitatus.cqrs.todo.app.domain.task.events.TaskStarredEvent;
import com.novitatus.cqrs.todo.app.domain.task.events.TaskTitleModifiedEvent;
import com.novitatus.cqrs.todo.app.domain.task.events.TaskUnstarredEvent;
import com.novitatus.cqrs.todo.app.query.task.TaskEntry;
import com.novitatus.cqrs.todo.app.query.task.TaskEntryRepository;

@Component
public class TaskEventNotifyingEventHandler
{
    private final SimpMessageSendingOperations messagingTemplate;
    private final TaskEntryRepository taskEntryRepository;

    public TaskEventNotifyingEventHandler(SimpMessageSendingOperations messagingTemplate, TaskEntryRepository taskEntryRepository)
    {
        this.messagingTemplate = messagingTemplate;
        this.taskEntryRepository = taskEntryRepository;
    }

    @EventHandler
    void on(TaskCreatedEvent event)
    {
        publish(event.getUsername(), event);
    }

    @EventHandler
    void on(TaskCompletedEvent event)
    {
        TaskEntry task = taskEntryRepository.findById(event.getId()).get();

        publish(task.getUsername(), event);
    }

    @EventHandler
    void on(TaskTitleModifiedEvent event)
    {
        TaskEntry task = taskEntryRepository.findById(event.getId()).get();

        publish(task.getUsername(), event);
    }

    @EventHandler
    void on(TaskStarredEvent event)
    {
        TaskEntry task = taskEntryRepository.findById(event.getId()).get();

        publish(task.getUsername(), event);
    }

    @EventHandler
    void on(TaskUnstarredEvent event)
    {
        TaskEntry task = taskEntryRepository.findById(event.getId()).get();

        publish(task.getUsername(), event);
    }

    private void publish(String username, TaskEvent event)
    {
        String type = event.getClass().getSimpleName();

        messagingTemplate.convertAndSendToUser(username, "/queue/task-updates", new TaskEventNotification(type, event));
    }
}
