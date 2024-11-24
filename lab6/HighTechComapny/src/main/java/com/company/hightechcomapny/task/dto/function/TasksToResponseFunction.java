package com.company.hightechcomapny.task.dto.function;

import com.company.hightechcomapny.task.dto.GetTasksResponse;
import com.company.hightechcomapny.task.entity.Task;

import java.util.List;
import java.util.function.Function;

public class TasksToResponseFunction implements Function<List<Task>, GetTasksResponse> {

    @Override
    public GetTasksResponse apply(List<Task> tasks) {
        return GetTasksResponse.builder()
                .tasks(tasks.stream()
                        .map(task -> GetTasksResponse.Task.builder()
                                .id(task.getId())
                                .description(task.getDescription())
                                .deadline(task.getDeadline())
                                .priority(task.getPriority())
                                .build())
                        .toList())
                .build();
    }
}
