package com.company.hightechcomapny.component;

import com.company.hightechcomapny.employee.model.function.EmployeeToModelFunction;
import com.company.hightechcomapny.project.model.function.ProjectToModelFunction;
import com.company.hightechcomapny.project.model.function.ProjectsToModelFunction;
import com.company.hightechcomapny.task.model.function.*;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ModelFunctionFactory {
    public ProjectToModelFunction projectToModel() {
        return new ProjectToModelFunction();
    }
    public ProjectsToModelFunction projectsToModel() {
        return new ProjectsToModelFunction();
    }
//    public ModelToProjectFunction modelToProject() {
//        return new ModelToProjectFunction();
//    }
//    public ModelToProjectFunction modelToProject() {
//        return new ModelToProjectFunction();
//    }

    public TaskToModelFunction taskToModel() {
        return new TaskToModelFunction();
    }
    public TasksToModelFunction tasksToModel() {
        return new TasksToModelFunction();
    }
    public TaskToEditModelFunction taskToEditModel() {
        return new TaskToEditModelFunction();
    }
    public TaskToCreateModelFunction taskToCreateModel() {
        return new TaskToCreateModelFunction();
    }
    public ModelToTaskFunction modelToTask() {
        return new ModelToTaskFunction();
    }
    public UpdateTaskWithModelFunction updateTask() {
        return new UpdateTaskWithModelFunction();
    }
    public EmployeeToModelFunction employeeToModel() {
        return new EmployeeToModelFunction();
    }
}
