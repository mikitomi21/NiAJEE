package com.company.hightechcomapny.project.service;

import com.company.hightechcomapny.employee.entity.Employee;
import com.company.hightechcomapny.project.entity.Project;
import com.company.hightechcomapny.project.repository.api.ProjectRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

import java.util.Optional;

@ApplicationScoped
@NoArgsConstructor(force = true)
public class ProjectService {
    private final ProjectRepository projectRepository;

    @Inject
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Optional<Project> find(String name) {
        return projectRepository.findByName(name);
    }

    public Optional<Project> find(UUID id) {
        return projectRepository.find(id);
    }

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Transactional
    public void create(Project project) {
        projectRepository.create(project);
    }

    @Transactional
    public void update(Project project) {
        projectRepository.update(project);
    }

    @Transactional
    public void delete(UUID id) {
        projectRepository.find(id).ifPresent(projectRepository::delete);
    }

}
