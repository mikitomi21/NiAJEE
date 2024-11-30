package com.company.hightechcomapny.task.model.function;

import com.company.hightechcomapny.employee.entity.Employee;
import com.company.hightechcomapny.employee.model.EmployeeModel;
import com.company.hightechcomapny.project.entity.Project;
import com.company.hightechcomapny.project.model.ProjectModel;
import com.company.hightechcomapny.project.model.ProjectsModel;
import com.company.hightechcomapny.task.entity.Task;
import com.company.hightechcomapny.task.model.TaskCreateModel;
import lombok.SneakyThrows;

import java.io.Serializable;
import java.util.function.Function;

public class ModelToTaskFunction implements Function<TaskCreateModel, Task>, Serializable {
    @Override
    @SneakyThrows
    public Task apply(TaskCreateModel taskCreateModel) {
        return Task.builder()
                .id(taskCreateModel.getId())
                .description(taskCreateModel.getDescription())
                .deadline(taskCreateModel.getDeadline())
                .priority(taskCreateModel.getPriority())
                .project(convertToProject(taskCreateModel.getProject()))
                .employee(taskCreateModel.getEmployee())
                .build();
    }

    private Project convertToProject(ProjectModel projectModel) {
        if (projectModel == null) {
            return null;
        }
        return Project.builder()
                .id(projectModel.getId())
                .name(projectModel.getName())
                .build();
    }

    private Employee convertToEmployee(EmployeeModel employeeModel) {
        if (employeeModel == null) {
            return null;
        }
        return Employee.builder()
                .id(employeeModel.getId())
                .name(employeeModel.getName())
                .build();
    }
}
