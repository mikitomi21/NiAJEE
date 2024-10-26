package com.company.hightechcomapny.task.service;

import com.company.hightechcomapny.employee.repository.api.EmployeeRepository;
import com.company.hightechcomapny.task.entity.Task;
import com.company.hightechcomapny.task.repository.api.TaskRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
@NoArgsConstructor(force = true)
public class TaskService {
    private final TaskRepository taskRepository;

    @Inject
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Optional<Task> find(UUID id) {
        return taskRepository.find(id);
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public void create(Task task) {
        taskRepository.create(task);
    }

    public void update(Task task) {
        System.out.println("TaskService.update called");
        taskRepository.update(task);
    }

    public void delete(UUID id) {
        taskRepository.delete(taskRepository.find(id).orElseThrow());
    }

}
