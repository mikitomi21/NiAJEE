package com.company.hightechcomapny.employee.service;

import com.company.hightechcomapny.employee.entity.Employee;
import com.company.hightechcomapny.employee.repository.api.EmployeeRepository;

import java.io.InputStream;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@ApplicationScoped
@NoArgsConstructor(force = true)
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Inject
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

    public void updatePicture(UUID id, InputStream picture, String filename) {
        employeeRepository.find(id).ifPresent(employee -> {
            try {
                if (filename == null) {
                    employee.setPicture(null);
                }
                else {
                    Path pictureDirectory = Paths.get("images");

                    if (!Files.exists(pictureDirectory)) {
                        Files.createDirectories(pictureDirectory);
                    }

                    Path picturePath = pictureDirectory.resolve(filename.replace("\\", "/"));

                    System.out.println(picturePath);
                    Files.copy(picture, picturePath, StandardCopyOption.REPLACE_EXISTING);
                    employee.setPicture("/images/" + filename);
                }
                employeeRepository.update(employee);
            } catch (IOException e) {
                throw new RuntimeException("Błąd podczas zapisu zdjęcia profilowego", e);
            }
        });
    }
}
