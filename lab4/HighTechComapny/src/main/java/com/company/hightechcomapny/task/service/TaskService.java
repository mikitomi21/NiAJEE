package com.company.hightechcomapny.task.service;

import com.company.hightechcomapny.project.repository.api.ProjectRepository;
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
    private final ProjectRepository projectRepository;

    @Inject
    public TaskService(TaskRepository taskRepository, ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
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

    public void deleteByProjectId(UUID projectId) {
        List<Task> tasks = taskRepository.findAllById(projectId);
        tasks.forEach(task -> delete(task.getId()));
    }

    public Optional<List<Task>> findAllByProject(UUID id) {
        return projectRepository.find(id).map(taskRepository::findAllByProject);
    }

    public Optional<Task> findByProjectAndTask(UUID projectId, UUID taskId) {
        return projectRepository.find(projectId)
                .flatMap(project -> taskRepository.find(taskId)
                        .filter(task -> task.getProject().getId().equals(projectId))
                );
    }
}
