package com.company.hightechcomapny.employee.repository.persistence;

import com.company.hightechcomapny.employee.entity.Employee;
import com.company.hightechcomapny.employee.repository.api.EmployeeRepository;
import com.company.hightechcomapny.project.entity.Project;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Dependent
public class EmployeePersistenceRepository implements EmployeeRepository {
    private EntityManager em;

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<Employee> findByName(String name) {
        return em.createQuery("select e from Employee e where e.name = :name", Employee.class)
                .setParameter("name", name)
                .getResultList()
                .stream()
                .findFirst();
    }

    @Override
    public Optional<Employee> find(UUID id) {
        return Optional.ofNullable(em.find(Employee.class, id));
    }

    @Override
    public List<Employee> findAll() {
        return em.createQuery("select e from Employee e", Employee.class).getResultList();
    }

    @Override
    public void create(Employee entity) {
        em.persist(entity);
    }

    @Override
    public void delete(Employee entity) {
        em.remove(em.find(Employee.class, entity.getId()));
    }

    @Override
    public void update(Employee entity) {
        em.merge(entity);
    }
}
