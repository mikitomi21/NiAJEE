package com.company.hightechcomapny.project.view;

import com.company.hightechcomapny.component.ModelFunctionFactory;
import com.company.hightechcomapny.project.model.ProjectsModel;
import com.company.hightechcomapny.project.service.ProjectService;
import com.company.hightechcomapny.task.service.TaskService;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;

@ViewScoped
@Named
public class ProjectList implements Serializable {
    private ProjectService service;
    private TaskService taskService;
    private ProjectsModel projects;
    private final ModelFunctionFactory factory;

    @Inject
    public ProjectList(ModelFunctionFactory factory) {
        this.factory = factory;
    }

    @EJB
    public void setTaskService(TaskService taskService){
        this.taskService = taskService;
    }
    @EJB
    public void setProjectService(ProjectService service){
        this.service = service;
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
