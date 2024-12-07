package com.company.hightechcomapny.task.repository.persistence;

import com.company.hightechcomapny.project.entity.Project;
import com.company.hightechcomapny.task.entity.Task;
import com.company.hightechcomapny.task.repository.api.TaskRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
//@Dependent
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
        if (!em.isJoinedToTransaction()) {
            em.joinTransaction();
        }
        em.persist(entity);

    }

    @Override
    public void delete(Task entity) {
        if (!em.isJoinedToTransaction()) {
            em.joinTransaction();
        }
        em.remove(em.find(Task.class, entity.getId()));

    }

    @Override
    public void update(Task entity) {
        if (!em.isJoinedToTransaction()) {
            em.joinTransaction();
        }
        em.merge(entity);

    }

    @Override
    public List<Task> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Task> query = cb.createQuery(Task.class);
        Root<Task> root = query.from(Task.class);
        query.select(root);
        return em.createQuery(query).getResultList();

    }

    @Override
    public List<Task> findAllById(UUID projectId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Task> query = cb.createQuery(Task.class);
        Root<Task> root = query.from(Task.class);

        // Tworzymy predykat, który sprawdza, czy projekt zadania ma odpowiedni id
        Predicate projectPredicate = cb.equal(root.get("project").get("id"), projectId);
        query.select(root).where(projectPredicate);

        return em.createQuery(query).getResultList();
    }

    @Override
    public List<Task> findAllByProject(Project project) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Task> query = cb.createQuery(Task.class);
        Root<Task> root = query.from(Task.class);

        // Tworzymy predykat, który sprawdza, czy projekt zadania jest równy przekazanemu projektowi
        Predicate projectPredicate = cb.equal(root.get("project"), project);
        query.select(root).where(projectPredicate);

        return em.createQuery(query).getResultList();
    }
}
