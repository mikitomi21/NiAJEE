package com.company.hightechcomapny.employee.service;

import com.company.hightechcomapny.employee.entity.Employee;
import com.company.hightechcomapny.employee.entity.EmployeeRoles;
import com.company.hightechcomapny.employee.repository.api.EmployeeRepository;

import java.io.InputStream;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import lombok.NoArgsConstructor;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//@ApplicationScoped
@LocalBean
@Stateless
@NoArgsConstructor(force = true)
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final Pbkdf2PasswordHash passwordHash;
    private final SecurityContext securityContext;

    @Inject
    public EmployeeService(EmployeeRepository employeeRepository, Pbkdf2PasswordHash passwordHash, SecurityContext securityContext) {
        this.employeeRepository = employeeRepository;
        this.passwordHash = passwordHash;
        this.securityContext = securityContext;
    }

    @RolesAllowed(EmployeeRoles.ADMIN)
    public Optional<Employee> find(String name) {
        return employeeRepository.findByName(name);
    }

    @RolesAllowed(EmployeeRoles.ADMIN)
    public Optional<Employee> find(UUID id) {
        return employeeRepository.find(id);
    }

    @RolesAllowed(EmployeeRoles.ADMIN)
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }


    @PermitAll
    public void create(Employee employee) {
        employee.setPassword(passwordHash.generate(employee.getPassword().toCharArray()));
        employeeRepository.create(employee);
    }


    @PermitAll
    public void update(Employee employee) {
        System.out.println(employee.getRoles());
        employeeRepository.update(employee);
    }


    @RolesAllowed(EmployeeRoles.ADMIN)
    public void delete(UUID id) {
        employeeRepository.find(id).ifPresent(employeeRepository::delete);
    }


    @RolesAllowed(EmployeeRoles.ADMIN)
    public void updatePicture(UUID id, InputStream picture, String filename) {
        employeeRepository.find(id).ifPresent(employee -> {
            try {
                if (filename == null) {
                    employee.setPicture(null);
                } else {
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

    @PermitAll
    public boolean verify(String login, String password) {
        return find(login)
                .map(user -> passwordHash.verify(password.toCharArray(), user.getPassword()))
                .orElse(false);
    }
}


//
//    @PermitAll
//    public void updateCallerPrincipalLastLoginDateTime() {
//        findCallerPrincipal().ifPresent(principal -> principal.setLastLoginDateTime(LocalDateTime.now()));
//    }
//
//    public Optional<Employee> findCallerPrincipal() {
//        if (securityContext.getCallerPrincipal() != null) {
//            return find(securityContext.getCallerPrincipal().getName());
//        } else {
//            return Optional.empty();
//        }
//    }


