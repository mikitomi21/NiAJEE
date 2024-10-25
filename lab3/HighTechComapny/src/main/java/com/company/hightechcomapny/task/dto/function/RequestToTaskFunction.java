package com.company.hightechcomapny.task.dto.function;

import com.company.hightechcomapny.task.dto.PutTaskRequest;
import com.company.hightechcomapny.task.entity.Task;

import java.util.UUID;
import java.util.function.BiFunction;

public class RequestToTaskFunction implements BiFunction<UUID, PutTaskRequest, Task> {
    @Override
    public Task apply(UUID uuid, PutTaskRequest putTaskRequest) {
        return Task.builder()
                .id(uuid)
                .description(putTaskRequest.getDescription())
                .deadline(putTaskRequest.getDeadline())
                .priority(putTaskRequest.getPriority())
                .build();
    }
}
