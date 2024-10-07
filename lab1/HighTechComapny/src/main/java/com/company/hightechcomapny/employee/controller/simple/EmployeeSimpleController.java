package com.company.hightechcomapny.employee.controller.simple;

import com.company.hightechcomapny.component.DtoFunctionFactory;
import com.company.hightechcomapny.employee.controller.api.EmployeeController;
import com.company.hightechcomapny.employee.dto.GetEmployeeResponse;
import com.company.hightechcomapny.employee.dto.GetEmployeesResponse;
import com.company.hightechcomapny.employee.dto.PatchEmployeeRequest;
import com.company.hightechcomapny.employee.dto.PutEmployeeRequest;
import com.company.hightechcomapny.employee.entity.Employee;
import com.company.hightechcomapny.employee.service.EmployeeService;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

import java.io.InputStream;
import java.util.UUID;

public class EmployeeSimpleController implements EmployeeController {
    private final EmployeeService service;
    private final DtoFunctionFactory factory;
    public EmployeeSimpleController(EmployeeService employeeService, DtoFunctionFactory dtoFunctionFactory) {
        this.service = employeeService;
        this.factory = dtoFunctionFactory;
    }

    @Override
    public GetEmployeesResponse getEmployees() {
        return factory.employeesToResponse().apply(service.findAll());
    }

    @Override
    public GetEmployeeResponse getEmployee(UUID id) {
        return service.find(id)
                .map(factory.employeeToResponse())
                .orElse(null);
    }

    @Override
    public void putEmployee(UUID id, PutEmployeeRequest request) {
        try {
            service.create(factory.requestToEmployee().apply(id, request));
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException(ex);
        }
    }

    @Override
    public void patchEmployee(UUID id, PatchEmployeeRequest request) {
        service.find(id).ifPresentOrElse(
                entity -> service.update(factory.updateEmployee().apply(entity, request)),
                () -> {
                    throw new NotFoundException();
                }
        );

    }

    @Override
    public void deleteEmployee(UUID id) {
        service.find(id).ifPresentOrElse(
                entity -> service.delete(id),
                () -> {
                    throw new NotFoundException();
                }
        );

    }

    @Override
    public byte[] getEmployeePicture(UUID id) {
        return service.find(id)
                .map(Employee::getPicture)
                .orElseThrow(NotFoundException::new);

    }

    @Override
    public void putEmployeePicture(UUID id, InputStream picture) {
        service.find(id).ifPresentOrElse(
                entity -> service.updatePicture(id, picture),
                () -> {
                    throw new NotFoundException();
                }
        );

    }
}
