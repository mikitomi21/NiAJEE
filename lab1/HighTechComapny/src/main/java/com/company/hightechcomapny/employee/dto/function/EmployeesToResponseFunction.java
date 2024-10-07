package com.company.hightechcomapny.employee.dto.function;

import com.company.hightechcomapny.employee.dto.GetEmployeesResponse;
import com.company.hightechcomapny.employee.entity.Employee;

import java.util.List;
import java.util.function.Function;

public class EmployeesToResponseFunction implements Function<List<Employee>, GetEmployeesResponse> {
    @Override
    public GetEmployeesResponse apply(List<Employee> employees) {
        return GetEmployeesResponse.builder()
                .employees(employees.stream()
                        .map(employee -> GetEmployeesResponse.Employee.builder()
                                .id(employee.getId())
                                .name(employee.getName())
                                .build())
                        .toList())
                .build();
    }
}
