package com.company.hightechcomapny.employee.dto.function;

import com.company.hightechcomapny.employee.dto.GetEmployeeResponse;
import com.company.hightechcomapny.employee.entity.Employee;

import java.util.function.Function;

public class EmployeeToResponseFunction implements Function<Employee, GetEmployeeResponse> {
    @Override
    public GetEmployeeResponse apply(Employee employee) {
        return GetEmployeeResponse.builder()
                .id(employee.getId())
                .name(employee.getName())
                .build();
    }
}
