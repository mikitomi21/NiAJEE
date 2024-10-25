package com.company.hightechcomapny.task.view;

import com.company.hightechcomapny.component.ModelFunctionFactory;
import com.company.hightechcomapny.task.model.TasksModel;
import com.company.hightechcomapny.task.service.TaskService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@RequestScoped
@Named
public class TaskList {
    private final TaskService service;
    private TasksModel tasks;
    private final ModelFunctionFactory factory;

    @Inject
    public TaskList(TaskService service, ModelFunctionFactory factory) {
        this.service = service;
        this.factory = factory;
    }

    public TasksModel getTasks() {
        if (tasks == null) {
            tasks = factory.tasksToModel().apply(service.findAll());
        }
        return tasks;
    }

    public String deleteAction(TasksModel.Task task) {
        service.delete(task.getId());
        return "task-list?faces-redirect=true";
    }
}
