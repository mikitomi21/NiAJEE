package com.company.hightechcomapny.employee.controller.api;

import com.company.hightechcomapny.employee.dto.GetEmployeeResponse;
import com.company.hightechcomapny.employee.dto.GetEmployeesResponse;
import com.company.hightechcomapny.employee.dto.PatchEmployeeRequest;
import com.company.hightechcomapny.employee.dto.PutEmployeeRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.io.InputStream;
import java.util.UUID;

public interface EmployeeController {

    @GET
    @Path("/employees")
    @Produces(MediaType.APPLICATION_JSON)
    GetEmployeesResponse getEmployees();

    @GET
    @Path("/employees/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    GetEmployeeResponse getEmployee(@PathParam("id") UUID id);

    @PUT
    @Path("/employees/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    void putEmployee(@PathParam("id") UUID id, PutEmployeeRequest request);

    @PATCH
    @Path("/employees/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    void patchEmployee(@PathParam("id") UUID id, PatchEmployeeRequest request);

    @DELETE
    @Path("/employees/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    void deleteEmployee(@PathParam("id") UUID id);


//    byte[] getEmployeePicture(@PathParam("id") UUID id);
//
//    void putEmployeePicture(@PathParam("id") UUID id, InputStream picture, String filename);
//
//    void patchEmployeePicture(@PathParam("id") UUID uuid, InputStream picture, String filename);
//
//    void deleteEmployeePicture(@PathParam("id") UUID uuid);
}
