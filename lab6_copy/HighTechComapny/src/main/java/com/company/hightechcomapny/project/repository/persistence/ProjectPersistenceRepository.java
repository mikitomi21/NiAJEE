package com.company.hightechcomapny.project.repository.persistence;

import com.company.hightechcomapny.project.entity.Project;
import com.company.hightechcomapny.project.repository.api.ProjectRepository;
import com.company.hightechcomapny.task.entity.Task;
import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Dependent
public class ProjectPersistenceRepository implements ProjectRepository {
    private EntityManager em;

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<Project> findByName(String name) {
        return em.createQuery("select p from Project p where p.name = :name", Project.class)
                .setParameter("name", name)
                .getResultList()
                .stream()
                .findFirst();
    }

    @Override
    public Optional<Project> find(UUID id) {
        return Optional.ofNullable(em.find(Project.class, id));
    }

    @Override
    public List<Project> findAll() {
        return em.createQuery("select p from Project p", Project.class).getResultList();
    }

    @Override
    public void create(Project entity) {
        System.out.println("projekt------");
        em.persist(entity);
        System.out.println("projekt------");
    }

    @Override
    public void delete(Project entity) {
        em.remove(em.find(Project.class, entity.getId()));
    }

    @Override
    public void update(Project entity) {
        em.merge(entity);
    }
}
