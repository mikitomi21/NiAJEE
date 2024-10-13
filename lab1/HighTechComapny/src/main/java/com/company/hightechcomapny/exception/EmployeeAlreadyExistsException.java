package com.company.hightechcomapny.exception;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public class EmployeeAlreadyExistsException extends WebApplicationException {
    public EmployeeAlreadyExistsException(String message) {
        super(Response.status(Response.Status.CONFLICT)
                .entity(message)
                .build());
    }
}