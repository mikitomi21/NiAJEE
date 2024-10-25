package com.company.hightechcomapny.project.view;

import com.company.hightechcomapny.component.ModelFunctionFactory;
import com.company.hightechcomapny.project.model.ProjectsModel;
import com.company.hightechcomapny.project.service.ProjectService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@RequestScoped
@Named
public class ProjectList {
    private final ProjectService service;
    private ProjectsModel projects;
    private final ModelFunctionFactory factory;

    @Inject
    public ProjectList(ProjectService service, ModelFunctionFactory factory) {
        this.service = service;
        this.factory = factory;
    }

    public ProjectsModel getProjects() {
        if (projects == null) {
            projects = factory.projectsToModel().apply(service.findAll());
        }
        return projects;
    }

    public void deleteAction(ProjectsModel.Project project){
        service.delete(project.getId());
        projects = null;
    }
}
