package com.company.hightechcomapny.project.controller.api;

import com.company.hightechcomapny.project.dto.GetProjectResponse;
import com.company.hightechcomapny.project.dto.GetProjectsResponse;
import com.company.hightechcomapny.project.dto.PatchProjectRequest;
import com.company.hightechcomapny.project.dto.PutProjectRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.UUID;

public interface ProjectController {
    @GET
    @Path("/projects/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    GetProjectResponse getProject(@PathParam("id") UUID id);
    @GET
    @Path("/projects")
    @Produces(MediaType.APPLICATION_JSON)
    GetProjectsResponse getProjects();
    @PUT
    @Path("/projects/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    void putProject(@PathParam("id") UUID id, PutProjectRequest request);
    @PATCH
    @Path("/projects/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    void patchProject(@PathParam("id") UUID id, PatchProjectRequest request);
    @DELETE
    @Path("/projects/{id}")
    void deleteProject(@PathParam("id") UUID id);
}
