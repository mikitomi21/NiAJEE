package com.company.hightechcomapny.task.repository.api;

import com.company.hightechcomapny.repository.api.Repository;
import com.company.hightechcomapny.task.entity.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepository extends Repository<Task, UUID> {
    List<Task> findAll();
}
