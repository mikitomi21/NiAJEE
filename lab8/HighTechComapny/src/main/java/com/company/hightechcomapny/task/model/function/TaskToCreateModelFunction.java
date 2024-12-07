package com.company.hightechcomapny.task.model.function;

import com.company.hightechcomapny.employee.entity.Employee;
import com.company.hightechcomapny.employee.model.EmployeeModel;
import com.company.hightechcomapny.project.entity.Project;
import com.company.hightechcomapny.project.model.ProjectModel;
import com.company.hightechcomapny.task.entity.Task;
import com.company.hightechcomapny.task.model.TaskCreateModel;
import com.company.hightechcomapny.task.model.TaskEditModel;

import java.io.Serializable;
import java.util.function.Function;

public class TaskToCreateModelFunction implements Function<Task, TaskCreateModel>, Serializable {
    @Override
    public TaskCreateModel apply(Task task) {
        return TaskCreateModel.builder()
                .description(task.getDescription())
                .deadline(task.getDeadline())
                .priority(task.getPriority())
                .employee(task.getEmployee())
                .project(convertToProjectModel(task.getProject()))
                .build();
    }
    private EmployeeModel convertToEmployeeModel(Employee employee) {
        if (employee == null) {
            return null;
        }
        return EmployeeModel.builder()
                .id(employee.getId())
                .name(employee.getName())
                .build();
    }

    private ProjectModel convertToProjectModel(Project project) {
        if (project == null) {
            return null;
        }
        return ProjectModel.builder()
                .id(project.getId())
                .name(project.getName())
                .build();
    }
}
