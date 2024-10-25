package com.company.hightechcomapny.project.controller.simple;

import com.company.hightechcomapny.component.DtoFunctionFactory;
import com.company.hightechcomapny.project.controller.api.ProjectController;
import com.company.hightechcomapny.project.dto.GetProjectResponse;
import com.company.hightechcomapny.project.dto.GetProjectsResponse;
import com.company.hightechcomapny.project.dto.PatchProjectRequest;
import com.company.hightechcomapny.project.dto.PutProjectRequest;
import com.company.hightechcomapny.project.entity.Project;
import com.company.hightechcomapny.project.service.ProjectService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.UUID;

@RequestScoped
public class ProjectSimpleController implements ProjectController {
    private final ProjectService service;
    private final DtoFunctionFactory factory;

    @Inject
    public ProjectSimpleController(ProjectService service, DtoFunctionFactory factory) {
        this.service = service;
        this.factory = factory;
    }

    @Override
    public GetProjectResponse getProject(UUID id) {
        return service.find(id)
                .map(factory.projectToResponse())
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public GetProjectsResponse getProjects() {
        List<Project> projects = service.findAll();
        if (projects.isEmpty()) {
            throw new RuntimeException();
        }
        return factory.projectsToResponse().apply(projects);
    }

    @Override
    public void putProject(UUID id, PutProjectRequest request) {
        if (service.find(id).isPresent()) {
            System.out.println("Project with ID: " + id + " already exists.");
            throw new RuntimeException("Project with ID: " + id + " already exists.");
        }
        try {
            service.create(factory.requestToProject().apply(id, request));
        } catch (IllegalArgumentException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void patchProject(UUID id, PatchProjectRequest request) {
        if (service.find(id).isEmpty()) {
            System.out.println("Project with ID: " + id + " does not exist.");
            throw new RuntimeException("Project with ID: " + id + " does not exist.");
        }
        try {
            service.find(id).ifPresent(project -> service.update(factory.updateProject().apply(project, request)));
        } catch (IllegalArgumentException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void deleteProject(UUID id) {
        service.find(id).ifPresentOrElse(
                entity -> service.delete(id),
                () -> {
                    System.out.println("Project with ID: " + id + " does not exist.");
                    throw new RuntimeException("Project with ID: " + id + " does not exist.");
                }
        );
    }
}
