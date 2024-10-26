package com.company.hightechcomapny.task.repository.memory;

import com.company.hightechcomapny.datastore.DataStore;
import com.company.hightechcomapny.task.entity.Task;
import com.company.hightechcomapny.task.repository.api.TaskRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestScoped
public class TaskInMemoryRepository implements TaskRepository {
    private final DataStore store;

    @Inject
    public TaskInMemoryRepository(DataStore store) {
        this.store = store;
    }

    @Override
    public Optional<Task> find(UUID id) {
        if (id == null) {
            System.out.println("TaskInMemoryRepository.find: id is null");
            return Optional.empty(); // lub rzucenie wyjątku
        }
        return store.findAllTasks().stream()
                .filter(task -> task.getId() != null && task.getId().equals(id)) // sprawdź, czy id nie jest null
                .findFirst();
    }

    @Override
    public void create(Task entity) {
        System.out.println("TaskInMemoryRepository.create: " + entity);
        System.out.println("DupaDupa");
        store.createTask(entity);
    }

    @Override
    public void delete(Task entity) {
        store.deleteTask(entity);
    }

    @Override
    public void update(Task entity) {
        store.updateTask(entity);
    }

    @Override
    public List<Task> findAll() {
        return store.findAllTasks();
    }
}
