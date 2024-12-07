package com.company.hightechcomapny.task.service;

import com.company.hightechcomapny.employee.entity.Employee;
import com.company.hightechcomapny.employee.entity.EmployeeRoles;
import com.company.hightechcomapny.employee.repository.api.EmployeeRepository;
import com.company.hightechcomapny.project.repository.api.ProjectRepository;
import com.company.hightechcomapny.task.entity.Task;
import com.company.hightechcomapny.task.repository.api.TaskRepository;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@LocalBean
@Stateless
@NoArgsConstructor(force = true)
@Log
public class TaskService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;

    @Inject
    public TaskService(TaskRepository taskRepository, ProjectRepository projectRepository, EmployeeRepository employeeRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.employeeRepository = employeeRepository;
    }

    public Optional<Task> find(UUID id) {
        return taskRepository.find(id);
    }

    @PermitAll
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @PermitAll
    public void create(Task task) {
        System.out.println(task);
        taskRepository.create(task);
    }

//    @RolesAllowed(EmployeeRoles.ADMIN)
    public void update(Task task) {
        System.out.println("TaskService.update called");
        taskRepository.update(task);
    }

//    @RolesAllowed(EmployeeRoles.ADMIN)
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
