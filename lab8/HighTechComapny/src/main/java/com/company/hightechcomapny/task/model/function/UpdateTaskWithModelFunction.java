package com.company.hightechcomapny.task.model.function;

import com.company.hightechcomapny.employee.entity.Employee;
import com.company.hightechcomapny.employee.model.EmployeeModel;
import com.company.hightechcomapny.project.entity.Project;
import com.company.hightechcomapny.project.model.ProjectModel;
import com.company.hightechcomapny.task.entity.Task;
import com.company.hightechcomapny.task.model.TaskEditModel;

import java.io.Serializable;
import java.util.function.BiFunction;

public class UpdateTaskWithModelFunction implements BiFunction<Task, TaskEditModel, Task>, Serializable {
    @Override
    public Task apply(Task task, TaskEditModel taskEditModel) {
        System.out.println(task);
        System.out.println(taskEditModel);
        return Task.builder()
                .id(task.getId())
                .description(taskEditModel.getDescription())
                .deadline(taskEditModel.getDeadline())
                .priority(taskEditModel.getPriority())
                .employee(convertToEmployee(taskEditModel.getEmployee()))
                .project(convertToProject(taskEditModel.getProject()))
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

    private Project convertToProject(ProjectModel projectModel) {
        if (projectModel == null) {
            return null;
        }
        return Project.builder()
                .id(projectModel.getId())
                .name(projectModel.getName())
                .build();
    }
}
