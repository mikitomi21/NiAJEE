package com.company.hightechcomapny.employee.controller.rest;

import com.company.hightechcomapny.component.DtoFunctionFactory;
import com.company.hightechcomapny.employee.controller.api.EmployeeController;
import com.company.hightechcomapny.employee.dto.GetEmployeeResponse;
import com.company.hightechcomapny.employee.dto.GetEmployeesResponse;
import com.company.hightechcomapny.employee.dto.PatchEmployeeRequest;
import com.company.hightechcomapny.employee.dto.PutEmployeeRequest;
import com.company.hightechcomapny.employee.entity.EmployeeRoles;
import com.company.hightechcomapny.employee.service.EmployeeService;
import com.company.hightechcomapny.project.service.ProjectService;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import lombok.extern.java.Log;

import java.util.UUID;

@Path("")
@RolesAllowed({EmployeeRoles.ADMIN, EmployeeRoles.USER})
@Log
public class EmployeeRestController implements EmployeeController {
    private EmployeeService service;
    private final DtoFunctionFactory factory;
    private final UriInfo uriInfo;
    private HttpServletResponse response;

    @Context
    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }
    @Inject

    public EmployeeRestController( DtoFunctionFactory factory, UriInfo uriInfo) {
        this.factory = factory;
        this.uriInfo = uriInfo;
    }

    @EJB
    public void setService(EmployeeService service) {
        this.service = service;
    }

    @Override
    public GetEmployeesResponse getEmployees() {
        System.out.println("Siema");
        return factory.employeesToResponse().apply(service.findAll());
    }

    @Override
    public GetEmployeeResponse getEmployee(UUID id) {
        return service.find(id)
                .map(factory.employeeToResponse())
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    @Override
    public void putEmployee(UUID id, PutEmployeeRequest request) {
        try {
            service.create(factory.requestToEmployee().apply(id, request));
            response.setHeader("Location", uriInfo.getBaseUriBuilder()
                    .path(EmployeeController.class, "getEmployee")
                    .build(id)
                    .toString());
            throw new WebApplicationException(Response.Status.CREATED);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException(e);
        }
    }

    @Override
    public void patchEmployee(UUID id, PatchEmployeeRequest request) {
        service.find(id).ifPresentOrElse(
                employee ->
                        service.update(factory.updateEmployee().apply(employee, request)),
                () -> {
                    throw new NotFoundException();
                }
        );
    }


    @Override
    public void deleteEmployee(UUID id) {
        service.find(id).ifPresentOrElse(
                employee -> service.delete(id),
                () -> {
                    throw new NotFoundException();
                }
        );
    }
}
