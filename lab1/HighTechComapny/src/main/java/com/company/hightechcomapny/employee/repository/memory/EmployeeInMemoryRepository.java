package com.company.hightechcomapny.employee.repository.memory;

import com.company.hightechcomapny.datastore.DataStore;
import com.company.hightechcomapny.employee.entity.Employee;
import com.company.hightechcomapny.employee.repository.api.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class EmployeeInMemoryRepository implements EmployeeRepository {
    private List<Employee> employees;
    public EmployeeInMemoryRepository() {
        employees = new ArrayList<>();
        employees.add(Employee.builder()
                .id(UUID.randomUUID())
                .name("Jakub")
                .salary(10000)
                .build());
        employees.add(Employee.builder()
                .id(UUID.randomUUID())
                .name("Marek")
                .salary(20000)
                .build());
        employees.add(Employee.builder()
                .id(UUID.randomUUID())
                .name("Katarzyna")
                .salary(100)
                .build());
        employees.add(Employee.builder()
                .id(UUID.randomUUID())
                .name("Dariusz")
                .salary(6900)
                .build());
    }

    @Override
    public Optional<Employee> findByName(String name) {
        return employees.stream()
                .filter(e -> e.getName().equals(name))
                .findFirst();
    }

    @Override
    public Optional<Employee> find(UUID id) {
        return employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Employee> findAll() {
        return new ArrayList<>(employees);
    }

    @Override
    public void create(Employee entity) {
        employees.add(entity);
    }

    @Override
    public void delete(Employee entity) {
        employees.remove(entity);
    }

    @Override
    public void update(Employee entity) {
        employees.remove(entity);
        employees.add(entity);
    }
}
