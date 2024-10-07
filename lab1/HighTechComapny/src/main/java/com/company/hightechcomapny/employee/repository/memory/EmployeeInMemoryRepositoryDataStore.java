package com.company.hightechcomapny.employee.repository.memory;

import com.company.hightechcomapny.datastore.DataStore;
import com.company.hightechcomapny.employee.entity.Employee;
import com.company.hightechcomapny.employee.repository.api.EmployeeRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class EmployeeInMemoryRepositoryDataStore implements EmployeeRepository {

    private final DataStore store;

    public EmployeeInMemoryRepositoryDataStore(DataStore store) {
        this.store = store;
    }

    @Override
    public Optional<Employee> findByName(String name) {
        return store.findAllEmployees().stream()
                .filter(e -> e.getName().equals(name))
                .findFirst();
    }

    @Override
    public Optional<Employee> find(UUID id) {
        return store.findAllEmployees().stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Employee> findAll() {
        return store.findAllEmployees();
    }

    @Override
    public void create(Employee entity) {
        store.createEmployee(entity);
    }

    @Override
    public void delete(Employee entity) {
        store.deleteEmployee(entity);
    }

    @Override
    public void update(Employee entity) {
        store.updateEmployee(entity);
    }
}
