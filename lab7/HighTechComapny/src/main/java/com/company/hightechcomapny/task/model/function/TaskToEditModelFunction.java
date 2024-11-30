package com.company.hightechcomapny.task.model.function;

import com.company.hightechcomapny.employee.model.EmployeeModel;
import com.company.hightechcomapny.project.model.ProjectModel;
import com.company.hightechcomapny.task.entity.Task;
import com.company.hightechcomapny.task.model.TaskEditModel;

import java.io.Serializable;
import java.util.function.Function;

public class TaskToEditModelFunction implements Function<Task, TaskEditModel>, Serializable {
    @Override
    public TaskEditModel apply(Task task) {
        System.out.println(task);
        return TaskEditModel.builder()
                .description(task.getDescription())
                .deadline(task.getDeadline())
                .priority(task.getPriority())
                .project(ProjectModel.builder()
                        .id(task.getProject().getId())
                        .name(task.getProject().getName())
                        .build())
                .employee(EmployeeModel.builder()
                        .id(task.getEmployee().getId())
                        .name(task.getEmployee().getName())
                        .build())
                .build();
    }
}
