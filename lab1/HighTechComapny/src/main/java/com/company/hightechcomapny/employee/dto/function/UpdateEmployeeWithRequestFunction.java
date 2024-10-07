package com.company.hightechcomapny.employee.dto.function;

import com.company.hightechcomapny.employee.dto.PatchEmployeeRequest;
import com.company.hightechcomapny.employee.entity.Employee;

import java.util.function.BiFunction;

public class UpdateEmployeeWithRequestFunction implements BiFunction<Employee, PatchEmployeeRequest, Employee> {
    @Override
    public Employee apply(Employee employee, PatchEmployeeRequest patchEmployeeRequest) {
        return null;
    }
}
