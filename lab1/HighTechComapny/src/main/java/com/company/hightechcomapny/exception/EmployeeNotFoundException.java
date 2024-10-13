package com.company.hightechcomapny.exception;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public class EmployeeNotFoundException extends WebApplicationException {
    public EmployeeNotFoundException() {
        super(Response.status(Response.Status.NOT_FOUND)
                .entity("Not Found...")
                .build());
    }
}