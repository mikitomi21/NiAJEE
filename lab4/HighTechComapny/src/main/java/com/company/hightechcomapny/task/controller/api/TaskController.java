package com.company.hightechcomapny.task.controller.api;

import com.company.hightechcomapny.task.dto.GetTaskResponse;
import com.company.hightechcomapny.task.dto.GetTasksResponse;
import com.company.hightechcomapny.task.dto.PatchTaskRequest;
import com.company.hightechcomapny.task.dto.PutTaskRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.UUID;

public interface TaskController {
    @GET
    @Path("/tasks/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    GetTaskResponse getTask(@PathParam("id") UUID id);
    @GET
    @Path("/tasks")
    @Produces(MediaType.APPLICATION_JSON)
    GetTasksResponse getTasks();

    @GET
    @Path("/projects/{id}/tasks/")
    @Produces(MediaType.APPLICATION_JSON)
    GetTasksResponse getProjectTasks(@PathParam("id") UUID id);

    @PUT
    @Path("/tasks/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    void putTask(@PathParam("id") UUID id, PutTaskRequest request);

    @PATCH
    @Path("/tasks/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    void patchTask(@PathParam("id") UUID id, PatchTaskRequest request);

    @DELETE
    @Path("/tasks/{id}")
    void deleteTask(@PathParam("id") UUID id);
}
