package com.company.hightechcomapny.employee.service;

import com.company.hightechcomapny.employee.entity.Employee;
import com.company.hightechcomapny.employee.repository.api.EmployeeRepository;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Optional<Employee> find(String name) {
        return employeeRepository.findByName(name);
    }

    public Optional<Employee> find(UUID id) {
        return employeeRepository.find(id);
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public void create(Employee employee) {
        System.out.println(employee);
        employeeRepository.create(employee);
    }

    public void update(Employee employee) {
        employeeRepository.update(employee);
    }

    public void delete(UUID id) {
        employeeRepository.find(id).ifPresent(employeeRepository::delete);
    }

    public void updatePicture(UUID id, InputStream picture) {
        employeeRepository.find(id).ifPresent(employee -> {
            try {
                employee.setPicture(picture.readAllBytes());
                employeeRepository.update(employee);
            } catch (Exception e) {
                throw new RuntimeException("Failed to update profile picture", e);
            }
        });
    }
}
