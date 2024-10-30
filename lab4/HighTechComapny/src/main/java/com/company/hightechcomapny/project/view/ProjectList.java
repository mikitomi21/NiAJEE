package com.company.hightechcomapny.project.view;

import com.company.hightechcomapny.component.ModelFunctionFactory;
import com.company.hightechcomapny.project.model.ProjectsModel;
import com.company.hightechcomapny.project.service.ProjectService;
import com.company.hightechcomapny.task.service.TaskService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@RequestScoped
@Named
public class ProjectList {
    private final ProjectService service;
    private final TaskService taskService;
    private ProjectsModel projects;
    private final ModelFunctionFactory factory;

    @Inject
    public ProjectList(ProjectService service, TaskService taskService, ModelFunctionFactory factory) {
        this.service = service;
        this.taskService = taskService;
        this.factory = factory;
    }

    public ProjectsModel getProjects() {
        if (projects == null) {
            projects = factory.projectsToModel().apply(service.findAll());
        }
        return projects;
    }

    public void deleteAction(ProjectsModel.Project project){
        taskService.deleteByProjectId(project.getId());
        service.delete(project.getId());
        projects = null;
    }
}
