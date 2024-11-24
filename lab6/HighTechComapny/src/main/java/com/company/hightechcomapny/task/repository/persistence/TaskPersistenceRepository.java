package com.company.hightechcomapny.task.repository.persistence;

import com.company.hightechcomapny.project.entity.Project;
import com.company.hightechcomapny.task.entity.Task;
import com.company.hightechcomapny.task.repository.api.TaskRepository;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Dependent
public class TaskPersistenceRepository implements TaskRepository {
    private EntityManager em;
    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<Task> find(UUID id) {
        return Optional.ofNullable(em.find(Task.class, id));
    }

    @Override
    public void create(Task entity) {
        em.persist(entity);
    }

    @Override
    public void delete(Task entity) {
        em.remove(em.find(Task.class, entity.getId()));
    }

    @Override
    public void update(Task entity) {
        em.merge(entity);
    }

    @Override
    public List<Task> findAll() {
        return em.createQuery("select t from Task t", Task.class).getResultList();
    }

    @Override
    public List<Task> findAllById(UUID projectId) {
        return em.createQuery("select t from Task t where t.project.id = :projectId", Task.class)
                .setParameter("projectId", projectId)
                .getResultList();
    }

    @Override
    public List<Task> findAllByProject(Project project) {
        return em.createQuery("select t from Task t where t.project = :project", Task.class)
                .setParameter("project", project)
                .getResultList();

    }
}
