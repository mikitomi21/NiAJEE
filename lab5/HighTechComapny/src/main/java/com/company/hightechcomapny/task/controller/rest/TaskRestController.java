package com.company.hightechcomapny.task.controller.rest;

import com.company.hightechcomapny.component.DtoFunctionFactory;
import com.company.hightechcomapny.task.controller.api.TaskController;
import com.company.hightechcomapny.task.dto.GetTaskResponse;
import com.company.hightechcomapny.task.dto.GetTasksResponse;
import com.company.hightechcomapny.task.dto.PatchTaskRequest;
import com.company.hightechcomapny.task.dto.PutTaskRequest;
import com.company.hightechcomapny.task.service.TaskService;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.util.UUID;

@Path("")
public class TaskRestController implements TaskController {
    private final TaskService service;
    private final DtoFunctionFactory factory;
    private final UriInfo uriInfo;
    private HttpServletResponse response;

    @Context
    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }
    @Inject
    public TaskRestController(
            TaskService service,
            DtoFunctionFactory factory,
            @SuppressWarnings("CdiInjectionPointsInspection") UriInfo uriInfo
    ) {
        this.service = service;
        this.factory = factory;
        this.uriInfo = uriInfo;
    }



    @Override
    public GetTaskResponse getTask(UUID id) {
        return service.find(id)
                .map(factory.taskToResponse())
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    @Override
    public GetTasksResponse getTasks() {
        return factory.tasksToResponse().apply(service.findAll());
    }

    @Override
    public GetTasksResponse getProjectTasks(UUID id) {
        return service.findAllByProject(id)
                .map(factory.tasksToResponse())
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }

    @Override
    public GetTaskResponse getProjectTasks(UUID projectId, UUID taskId) {
        return service.findByProjectAndTask(projectId, taskId)
                .map(factory.taskToResponse())
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    @Override
    public void putTask(UUID id, PutTaskRequest request) {
        try {
            service.create(factory.requestToTask().apply(id, request));
            response.setHeader("Location", uriInfo.getBaseUriBuilder()
                    .path(TaskController.class, "getTask")
                    .build(id)
                    .toString());
            throw new WebApplicationException(Response.Status.CREATED);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException(e);
        }
    }

    @Override
    public void patchTask(UUID id, PatchTaskRequest request) {
        service.find(id).ifPresentOrElse(
                task ->
                    service.update(factory.updateTask().apply(task, request)),
                            () -> {
                                throw new NotFoundException();
                            }
                    );
                }

    @Override
    public void deleteTask(UUID id) {
        service.find(id).ifPresentOrElse(
                task -> service.delete(id),
                () -> {
                    throw new NotFoundException();
                }
        );
    }
}
