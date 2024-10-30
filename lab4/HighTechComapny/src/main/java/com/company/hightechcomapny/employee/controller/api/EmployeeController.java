package com.company.hightechcomapny.employee.controller.api;

import com.company.hightechcomapny.employee.dto.GetEmployeeResponse;
import com.company.hightechcomapny.employee.dto.GetEmployeesResponse;
import com.company.hightechcomapny.employee.dto.PatchEmployeeRequest;
import com.company.hightechcomapny.employee.dto.PutEmployeeRequest;

import java.io.InputStream;
import java.util.UUID;

public interface EmployeeController {
    GetEmployeesResponse getEmployees();
    GetEmployeeResponse getEmployee(UUID id);
    void putEmployee(UUID id, PutEmployeeRequest request);
    void patchEmployee(UUID id, PatchEmployeeRequest request);
    void deleteEmployee(UUID id);
    byte[] getEmployeePicture(UUID id);

    void putEmployeePicture(UUID id, InputStream picture, String filename);

    void patchEmployeePicture(UUID uuid, InputStream picture, String filename);

    void deleteEmployeePicture(UUID uuid);
}
