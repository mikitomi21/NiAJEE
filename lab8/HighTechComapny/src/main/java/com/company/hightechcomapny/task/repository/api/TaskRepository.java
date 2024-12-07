package com.company.hightechcomapny.task.repository.api;

import com.company.hightechcomapny.project.entity.Project;
import com.company.hightechcomapny.repository.api.Repository;
import com.company.hightechcomapny.task.entity.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepository extends Repository<Task, UUID> {
    List<Task> findAll();

    List<Task> findAllById(UUID projectId);

    List<Task> findAllByProject(Project project);
}
