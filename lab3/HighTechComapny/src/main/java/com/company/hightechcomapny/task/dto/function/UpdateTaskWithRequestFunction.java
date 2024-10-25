package com.company.hightechcomapny.task.dto.function;

import com.company.hightechcomapny.employee.dto.PatchEmployeeRequest;
import com.company.hightechcomapny.employee.entity.Employee;
import com.company.hightechcomapny.task.dto.PatchTaskRequest;
import com.company.hightechcomapny.task.entity.Task;

import java.util.function.BiFunction;

public class UpdateTaskWithRequestFunction implements BiFunction<Task, PatchTaskRequest, Task> {
    @Override
    public Task apply(Task task, PatchTaskRequest patchTaskRequest) {
        return Task.builder()
                .id(task.getId())
                .description(patchTaskRequest.getDescription())
                .deadline(patchTaskRequest.getDeadline())
                .priority(patchTaskRequest.getPriority())
                .build();
    }
}
