package com.company.hightechcomapny.task.model.function;

import com.company.hightechcomapny.project.entity.Project;
import com.company.hightechcomapny.project.model.ProjectsModel;
import com.company.hightechcomapny.task.entity.Task;
import com.company.hightechcomapny.task.model.TaskCreateModel;
import lombok.SneakyThrows;

import java.io.Serializable;
import java.util.function.Function;

public class ModelToTaskFunction implements Function<TaskCreateModel, Task>, Serializable {
    @Override
    @SneakyThrows
    public Task apply(TaskCreateModel taskCreateModel) {
        return Task.builder()
                .description(taskCreateModel.getDescription())
                .deadline(taskCreateModel.getDeadline())
                .priority(taskCreateModel.getPriority())
                .project(Project.builder()
                        .id(taskCreateModel.getProject().getId())
                        .build())
                .build();
    }
}
