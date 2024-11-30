package com.company.hightechcomapny.task.model.function;


import com.company.hightechcomapny.task.entity.Task;
import com.company.hightechcomapny.task.model.TasksModel;

import java.util.List;
import java.util.function.Function;

public class TasksToModelFunction implements Function<List<Task>, TasksModel> {

    @Override
    public TasksModel apply(List<Task> tasks) {
        return TasksModel.builder()
                .tasks(tasks.stream().map(task -> TasksModel.Task.builder()
                        .id(task.getId())
                        .description(task.getDescription())
                        .build())
                        .toList())
                .build();
    }
}
