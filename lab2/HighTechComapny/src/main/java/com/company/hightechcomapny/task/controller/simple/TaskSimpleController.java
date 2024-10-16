package com.company.hightechcomapny.task.controller.simple;

import com.company.hightechcomapny.component.DtoFunctionFactory;
import com.company.hightechcomapny.task.controller.api.TaskController;
import com.company.hightechcomapny.task.dto.GetTaskResponse;
import com.company.hightechcomapny.task.dto.GetTasksResponse;
import com.company.hightechcomapny.task.dto.PatchTaskRequest;
import com.company.hightechcomapny.task.dto.PutTaskRequest;
import com.company.hightechcomapny.task.service.TaskService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

import java.util.UUID;

@RequestScoped
public class TaskSimpleController implements TaskController {
    private final TaskService service;
    private final DtoFunctionFactory factory;

    @Inject
    public TaskSimpleController(TaskService service, DtoFunctionFactory factory) {
        this.service = service;
        this.factory = factory;
    }

    @Override
    public GetTaskResponse getTask(UUID id) {
        return service.find(id)
                .map(factory.taskToResponse())
                .orElseThrow();
    }

    @Override
    public GetTasksResponse getTasks() {
        return factory.tasksToResponse().apply(service.findAll());
    }

    @Override
    public void putTask(UUID id, PutTaskRequest request) {
        try {
            service.create(factory.requestToTask().apply(id, request));
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException(ex);
        }
    }

    @Override
    public void patchTask(UUID id, PatchTaskRequest request) {
        service.find(id).ifPresentOrElse(
                entity -> service.update(factory.updateTask().apply(entity, request)),
                () -> {
                    throw new NotFoundException();
                }
        );
    }

    @Override
    public void deleteTask(UUID id) {
        service.find(id).ifPresentOrElse(
                entity -> service.delete(id),
                () -> {
                    throw new NotFoundException();
                }
        );
    }
}
