package com.novitatus.cqrs.todo.app.rest.task;

import java.security.Principal;

import javax.validation.Valid;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.common.IdentifierFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.novitatus.cqrs.todo.app.domain.task.commands.CompleteTaskCommand;
import com.novitatus.cqrs.todo.app.domain.task.commands.CreateTaskCommand;
import com.novitatus.cqrs.todo.app.domain.task.commands.ModifyTaskTitleCommand;
import com.novitatus.cqrs.todo.app.domain.task.commands.StarTaskCommand;
import com.novitatus.cqrs.todo.app.domain.task.commands.UnstarTaskCommand;
import com.novitatus.cqrs.todo.app.query.task.TaskEntry;
import com.novitatus.cqrs.todo.app.query.task.TaskEntryRepository;
import com.novitatus.cqrs.todo.app.rest.task.requests.CreateTaskRequest;
import com.novitatus.cqrs.todo.app.rest.task.requests.ModifyTitleRequest;

@RestController
public class TaskController
{
    private final IdentifierFactory identifierFactory = IdentifierFactory.getInstance();

    private final TaskEntryRepository taskEntryRepository;
    private final SimpMessageSendingOperations messagingTemplate;
    private final CommandGateway commandGateway;

    public TaskController(TaskEntryRepository taskEntryRepository, SimpMessageSendingOperations messagingTemplate, CommandGateway commandGateway)
    {
        this.taskEntryRepository = taskEntryRepository;
        this.messagingTemplate = messagingTemplate;
        this.commandGateway = commandGateway;
    }

    @RequestMapping(value = "/api/tasks", method = RequestMethod.GET)
    public @ResponseBody Page<TaskEntry> findAll(Principal principal, @RequestParam(required = false, defaultValue = "false") boolean completed, Pageable pageable)
    {
        return taskEntryRepository.findByUsernameAndCompleted(principal.getName(), completed, pageable);
    }

    @RequestMapping(value = "/api/tasks", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void createTask(Principal principal, @RequestBody @Valid CreateTaskRequest request)
    {
        commandGateway.send(new CreateTaskCommand(identifierFactory.generateIdentifier(), principal.getName(), request.getTitle()));
    }

    @RequestMapping(value = "/api/tasks/{identifier}/title", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void createTask(@PathVariable String identifier, @RequestBody @Valid ModifyTitleRequest request)
    {
        commandGateway.send(new ModifyTaskTitleCommand(identifier, request.getTitle()));
    }

    @RequestMapping(value = "/api/tasks/{identifier}/complete", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void createTask(@PathVariable String identifier)
    {
        commandGateway.send(new CompleteTaskCommand(identifier));
    }

    @RequestMapping(value = "/api/tasks/{identifier}/star", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void starTask(@PathVariable String identifier)
    {
        commandGateway.send(new StarTaskCommand(identifier));
    }

    @RequestMapping(value = "/api/tasks/{identifier}/unstar", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void unstarTask(@PathVariable String identifier)
    {
        commandGateway.sendAndWait(new UnstarTaskCommand(identifier));
    }

    @ExceptionHandler
    public void handleException(Principal principal, Throwable exception)
    {
        messagingTemplate.convertAndSendToUser(principal.getName(), "/queue/errors", exception.getMessage());
    }
}
