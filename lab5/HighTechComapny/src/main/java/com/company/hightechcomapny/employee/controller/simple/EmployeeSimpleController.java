package com.company.hightechcomapny.employee.controller.simple;

import com.company.hightechcomapny.component.DtoFunctionFactory;
import com.company.hightechcomapny.employee.controller.api.EmployeeController;
import com.company.hightechcomapny.employee.dto.GetEmployeeResponse;
import com.company.hightechcomapny.employee.dto.GetEmployeesResponse;
import com.company.hightechcomapny.employee.dto.PatchEmployeeRequest;
import com.company.hightechcomapny.employee.dto.PutEmployeeRequest;
import com.company.hightechcomapny.employee.entity.Employee;
import com.company.hightechcomapny.employee.service.EmployeeService;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@RequestScoped
public class EmployeeSimpleController implements EmployeeController {
    private final EmployeeService service;
    private final DtoFunctionFactory factory;
    @Inject
    public EmployeeSimpleController(EmployeeService employeeService, DtoFunctionFactory dtoFunctionFactory) {
        this.service = employeeService;
        this.factory = dtoFunctionFactory;
    }

    @Override
    public GetEmployeesResponse getEmployees() {
        List<Employee> employees = service.findAll();
        if (employees.isEmpty()) {
            throw new RuntimeException();
        }
        return factory.employeesToResponse().apply(employees);
    }

    @Override
    public GetEmployeeResponse getEmployee(UUID id) {
        return service.find(id)
                .map(factory.employeeToResponse())
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public void putEmployee(UUID id, PutEmployeeRequest request) {
        if (service.find(id).isPresent()) {
            System.out.println("Employee with ID: " + id + " already exists.");
            throw new RuntimeException("Employee with ID: " + id + " already exists.");
        }

        try {
            service.create(factory.requestToEmployee().apply(id, request));
        } catch (IllegalArgumentException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void patchEmployee(UUID id, PatchEmployeeRequest request) {
        service.find(id).ifPresentOrElse(
                entity -> service.update(factory.updateEmployee().apply(entity, request)),
                () -> {
                    throw new RuntimeException();
                }
        );

    }

    @Override
    public void deleteEmployee(UUID id) {
        service.find(id).ifPresentOrElse(
                entity -> service.delete(id),
                () -> {
                    throw new RuntimeException();
                }
        );

    }

    @Override
    public byte[] getEmployeePicture(UUID id) {
        return service.find(id)
                .map(employee -> {
                    String picturePathStr = employee.getPicture();
                    if (picturePathStr == null || picturePathStr.isEmpty()) {
                        throw new RuntimeException();
                    }

                    try (InputStream inputStream = getClass().getResourceAsStream(picturePathStr)) {
                        if (inputStream == null) {
                            throw new RuntimeException("Plik nie został znaleziony: " + picturePathStr);
                        }
                        return inputStream.readAllBytes();
                    } catch (IOException e) {
                        throw new RuntimeException("Błąd podczas odczytu pliku: " + picturePathStr, e);
                    }
                })
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public void putEmployeePicture(UUID id, InputStream picture, String filename) {
        service.find(id).ifPresentOrElse(
                entity -> {
                    if (entity.getPicture() != null && !entity.getPicture().isEmpty()) {
                        throw new RuntimeException("Zdjecie już istnieje dla tego pracownika.");
                    }
                    service.updatePicture(id, picture, filename);
                },
                () -> {
                    throw new RuntimeException();
                }
        );
    }

    @Override
    public void patchEmployeePicture(UUID uuid, InputStream picture, String filename) {
        service.find(uuid).ifPresentOrElse(
                entity -> service.updatePicture(uuid, picture, filename),
                () -> {
                    throw new RuntimeException();
                }
        );
    }

    @Override
    public void deleteEmployeePicture(UUID uuid) {
        System.out.println("deleteEmployeePicture");
        service.find(uuid).ifPresentOrElse(
                entity -> {
                    if (entity.getPicture() == null || entity.getPicture().isEmpty()) {
                        throw new RuntimeException();
                    }
                    service.updatePicture(uuid, null, null);
                },
                () -> {
                    throw new RuntimeException();
                }
        );
    }
}
