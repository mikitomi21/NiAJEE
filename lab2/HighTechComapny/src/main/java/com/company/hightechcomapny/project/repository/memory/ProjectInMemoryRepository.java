package com.company.hightechcomapny.project.repository.memory;

import com.company.hightechcomapny.datastore.DataStore;
import com.company.hightechcomapny.employee.entity.Employee;
import com.company.hightechcomapny.project.entity.Project;
import com.company.hightechcomapny.project.repository.api.ProjectRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProjectInMemoryRepository implements ProjectRepository {
    private final DataStore store;

    public ProjectInMemoryRepository(DataStore store) {
        this.store = store;
    }

    @Override
    public Optional<Project> findByName(String name) {
        return store.findAllProjects().stream()
                .filter(e -> e.getName().equals(name))
                .findFirst();
    }

    @Override
    public Optional<Project> find(UUID id) {
        return store.findAllProjects().stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Project> findAll() {
        return store.findAllProjects();
    }

    @Override
    public void create(Project entity) {
        store.createProject(entity);
    }

    @Override
    public void delete(Project entity) {
        store.deleteProject(entity);
    }

    @Override
    public void update(Project entity) {
        store.updateProject(entity);
    }
}
