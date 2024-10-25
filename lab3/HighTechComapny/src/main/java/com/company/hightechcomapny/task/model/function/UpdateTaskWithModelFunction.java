package com.company.hightechcomapny.task.model.function;

import com.company.hightechcomapny.task.entity.Task;
import com.company.hightechcomapny.task.model.TaskEditModel;

import java.io.Serializable;
import java.util.function.BiFunction;

public class UpdateTaskWithModelFunction implements BiFunction<Task, TaskEditModel, Task>, Serializable {
    @Override
    public Task apply(Task task, TaskEditModel taskEditModel) {
        return Task.builder()
                .id(task.getId())
                .description(taskEditModel.getDescription())
                .deadline(task.getDeadline())
                .priority(task.getPriority())
                .employee(task.getEmployee())
                .project(task.getProject())
                .build();
    }
}
