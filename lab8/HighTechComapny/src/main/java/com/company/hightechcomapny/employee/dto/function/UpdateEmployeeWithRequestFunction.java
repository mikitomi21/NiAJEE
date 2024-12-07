package com.company.hightechcomapny.employee.dto.function;

import com.company.hightechcomapny.employee.dto.PatchEmployeeRequest;
import com.company.hightechcomapny.employee.entity.Employee;
import jakarta.enterprise.context.Dependent;

import java.util.function.BiFunction;


public class UpdateEmployeeWithRequestFunction implements BiFunction<Employee, PatchEmployeeRequest, Employee> {
    @Override
    public Employee apply(Employee employee, PatchEmployeeRequest patchEmployeeRequest) {
        return Employee.builder()
                .id(employee.getId())
                .name(patchEmployeeRequest.getName())
                .salary(patchEmployeeRequest.getSalary())
                .build();
    }
}
