package com.company.hightechcomapny.task.dto.function;

import com.company.hightechcomapny.employee.dto.PatchEmployeeRequest;
import com.company.hightechcomapny.employee.entity.Employee;
import com.company.hightechcomapny.project.entity.Project;
import com.company.hightechcomapny.task.dto.PatchTaskRequest;
import com.company.hightechcomapny.task.entity.Task;
import lombok.SneakyThrows;

import java.util.function.BiFunction;

public class UpdateTaskWithRequestFunction implements BiFunction<Task, PatchTaskRequest, Task> {
    @Override
    @SneakyThrows
    public Task apply(Task task, PatchTaskRequest patchTaskRequest) {
        return Task.builder()
                .id(task.getId())
                .description(patchTaskRequest.getDescription())
                .deadline(patchTaskRequest.getDeadline())
                .priority(patchTaskRequest.getPriority())
                .project(Project.builder()
                        .id(patchTaskRequest.getProject().getId())
                        .name(patchTaskRequest.getProject().getName())
                        .tasks(patchTaskRequest.getProject().getTasks())
                        .build())
                .build();
    }
}
