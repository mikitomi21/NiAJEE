package com.company.hightechcomapny.project.controller.rest;

import com.company.hightechcomapny.component.DtoFunctionFactory;
import com.company.hightechcomapny.project.controller.api.ProjectController;
import com.company.hightechcomapny.project.dto.GetProjectResponse;
import com.company.hightechcomapny.project.dto.GetProjectsResponse;
import com.company.hightechcomapny.project.dto.PatchProjectRequest;
import com.company.hightechcomapny.project.dto.PutProjectRequest;
import com.company.hightechcomapny.project.service.ProjectService;
import com.company.hightechcomapny.task.controller.api.TaskController;
import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.util.UUID;

@Path("")
public class ProjectRestController implements ProjectController {
    private ProjectService service;
    private final DtoFunctionFactory factory;

    private final UriInfo uriInfo;
    @Context
    private HttpServletResponse response;
    @Inject
    public ProjectRestController(
            DtoFunctionFactory factory,
            @SuppressWarnings("CdiInjectionPointsInspection") UriInfo uriInfo
    ) {
        this.factory = factory;
        this.uriInfo = uriInfo;
    }

    @EJB
    public void setService(ProjectService service) {
        this.service = service;
    }


    @Override
    public GetProjectResponse getProject(UUID id) {
        return service.find(id)
                .map(factory.projectToResponse())
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }

    @Override
    public GetProjectsResponse getProjects() {
        return factory.projectsToResponse().apply(service.findAll());
    }

    @Override
    public void putProject(UUID id, PutProjectRequest request) {
        try {
            service.create(factory.requestToProject().apply(id, request));
            response.setHeader("Location", uriInfo.getBaseUriBuilder()
                    .path(ProjectController.class, "getProject")
                    .build(id)
                    .toString());
            throw new WebApplicationException(Response.Status.CREATED);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException(e);
        }
    }

    @Override
    public void patchProject(UUID id, PatchProjectRequest request) {
        service.find(id).ifPresentOrElse(
                project ->
                    service.update(factory.updateProject().apply(project, request)),
                        () -> {
                            throw new WebApplicationException(Response.Status.NOT_FOUND);
                        }
                );

    }

    @Override
    public void deleteProject(UUID id) {
        service.find(id).ifPresentOrElse(
                project -> service.delete(id),
                () -> {
                    throw new WebApplicationException(Response.Status.NOT_FOUND);
                }
        );
    }
}
