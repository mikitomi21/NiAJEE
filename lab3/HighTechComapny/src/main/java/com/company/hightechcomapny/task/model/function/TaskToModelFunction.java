package com.company.hightechcomapny.task.model.function;

import com.company.hightechcomapny.task.entity.Task;
import com.company.hightechcomapny.task.model.TaskModel;

import java.io.Serializable;
import java.util.function.Function;

public class TaskToModelFunction implements Function<Task, TaskModel>, Serializable {
    @Override
    public TaskModel apply(Task task) {
        return TaskModel.builder()
                .description(task.getDescription())
                .deadline(task.getDeadline())
                .priority(task.getPriority())
                .project(task.getProject().getName())
                .build();
    }
}
