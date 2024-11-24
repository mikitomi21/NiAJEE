package com.company.hightechcomapny.project.view;

import com.company.hightechcomapny.component.ModelFunctionFactory;
import com.company.hightechcomapny.project.entity.Project;
import com.company.hightechcomapny.project.model.ProjectModel;
import com.company.hightechcomapny.project.service.ProjectService;
import com.company.hightechcomapny.task.entity.Task;
import com.company.hightechcomapny.task.service.TaskService;
import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ViewScoped
@Named
public class ProjectView implements Serializable {
    private ProjectService service;
    private TaskService taskService;
    private final ModelFunctionFactory factory;

    @Setter
    @Getter
    private UUID id;

    @Getter
    private ProjectModel project;

    @Inject
    public ProjectView(ModelFunctionFactory factory) {
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

    public void init() throws IOException {
        Optional<Project> project = service.find(id);
        if (project.isPresent()) {
            this.project = factory.projectToModel().apply(project.get());
            System.out.println("Loaded project: " + this.project.getName() + " with tasks: " + this.project.getTasks());
        } else {
            FacesContext.getCurrentInstance().getExternalContext().responseSendError(HttpServletResponse.SC_NOT_FOUND, "Character not found");
        }

    }

    public void deleteTask(Task task) {
        //TODO usuniecie z projktu na stale
        System.out.println("Deleting task with ID: " + task.getId());
        taskService.delete(task.getId());

        List<Task> tasks = new ArrayList<>(project.getTasks());
        tasks.removeIf(t -> t.getId().equals(task.getId()));
        project.setTasks(tasks);

        System.out.println("Updated project tasks: " + project.getTasks());
    }

}
