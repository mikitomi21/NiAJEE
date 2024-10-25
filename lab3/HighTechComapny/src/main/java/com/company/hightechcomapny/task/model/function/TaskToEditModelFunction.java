package com.company.hightechcomapny.task.model.function;

import com.company.hightechcomapny.task.entity.Task;
import com.company.hightechcomapny.task.model.TaskEditModel;

import java.io.Serializable;
import java.util.function.Function;

public class TaskToEditModelFunction implements Function<Task, TaskEditModel>, Serializable {
    @Override
    public TaskEditModel apply(Task task) {
        return TaskEditModel.builder()
                .description(task.getDescription())
                .build();
    }
}
