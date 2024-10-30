package com.company.hightechcomapny.datastore;

import com.company.hightechcomapny.employee.entity.Employee;
import com.company.hightechcomapny.project.entity.Project;
import com.company.hightechcomapny.task.entity.Task;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Log
@ApplicationScoped
@NoArgsConstructor(force = true)
public class DataStore {
    private final Set<Employee> employees = new HashSet<>();
    private final Set<Task> tasks = new HashSet<>();
    private final Set<Project> projects = new HashSet<>();


//    @Inject
//    public DataStore() {
//        log.info("DataStore created");
//    }

    // Employee
    public List<Employee> findAllEmployees() {
    //  return new ArrayList<>(employees);
        return List.copyOf(employees);
    }

    public void createEmployee(Employee employee) {
        if(employee == null) {
            throw new IllegalArgumentException("Employee cannot be null");
        }
        if(employees.stream().anyMatch(e -> e.getId().equals(employee.getId()))) {
            throw new IllegalArgumentException("Employee with id " + employee.getId() + " already exists");
        }
        employees.add(employee);
    }

    public void updateEmployee(Employee employee) {
        if(employee == null) {
            throw new IllegalArgumentException("Employee cannot be null");
        }
        if(employees.stream().noneMatch(e -> e.getId().equals(employee.getId()))) {
            throw new IllegalArgumentException("Employee with id " + employee.getId() + " does not exist");
        }
        employees.removeIf(e -> e.getId().equals(employee.getId()));
        employees.add(employee);
    }

    public void deleteEmployee(Employee employee) {
        if(employee == null) {
            throw new IllegalArgumentException("Employee cannot be null");
        }
        if(employees.stream().noneMatch(e -> e.getId().equals(employee.getId()))) {
            throw new IllegalArgumentException("Employee with id " + employee.getId() + " does not exist");
        }
        employees.removeIf(e -> e.getId().equals(employee.getId()));
    }

    // Task
    public List<Task> findAllTasks() {
        return List.copyOf(tasks);
    }

    public void createTask(Task task) {
        if(task == null) {
            throw new IllegalArgumentException("Task cannot be null");
        }
        if(tasks.stream().anyMatch(t -> t.getId().equals(task.getId()))) {
            throw new IllegalArgumentException("Task with id " + task.getId() + " already exists");
        }
        tasks.add(task);
    }

    public void updateTask(Task task) {
        if(task == null) {
            throw new IllegalArgumentException("Task cannot be null");
        }
        if(tasks.stream().noneMatch(t -> t.getId().equals(task.getId()))) {
            throw new IllegalArgumentException("Task with id " + task.getId() + " does not exist");
        }
        tasks.removeIf(t -> t.getId().equals(task.getId()));
        tasks.add(task);
    }

    public void deleteTask(Task task) {
        if(task == null) {
            throw new IllegalArgumentException("Task cannot be null");
        }
        if(tasks.stream().noneMatch(t -> t.getId().equals(task.getId()))) {
            throw new IllegalArgumentException("Task with id " + task.getId() + " does not exist");
        }
        tasks.removeIf(t -> t.getId().equals(task.getId()));
    }

    // Project
    public List<Project> findAllProjects() {
        return List.copyOf(projects);
    }

    public void createProject(Project project) {
        if(project == null) {
            throw new IllegalArgumentException("Project cannot be null");
        }
        if(projects.stream().anyMatch(p -> p.getId().equals(project.getId()))) {
            throw new IllegalArgumentException("Project with id " + project.getId() + " already exists");
        }
        projects.add(project);
    }

    public void updateProject(Project project) {
        if(project == null) {
            throw new IllegalArgumentException("Project cannot be null");
        }
        if(projects.stream().noneMatch(p -> p.getId().equals(project.getId()))) {
            throw new IllegalArgumentException("Project with id " + project.getId() + " does not exist");
        }
        projects.removeIf(p -> p.getId().equals(project.getId()));
        projects.add(project);
    }

    public void deleteProject(Project project) {
        if(project == null) {
            throw new IllegalArgumentException("Project cannot be null");
        }
        if(projects.stream().noneMatch(p -> p.getId().equals(project.getId()))) {
            throw new IllegalArgumentException("Project with id " + project.getId() + " does not exist");
        }
        projects.removeIf(p -> p.getId().equals(project.getId()));
    }
}
