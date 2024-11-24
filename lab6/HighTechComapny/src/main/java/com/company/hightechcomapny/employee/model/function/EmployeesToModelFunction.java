package com.company.hightechcomapny.employee.model.function;

import com.company.hightechcomapny.employee.entity.Employee;
import com.company.hightechcomapny.employee.model.EmployeesModel;

import java.util.List;
import java.util.function.Function;

public class EmployeesToModelFunction implements Function<List<Employee>, EmployeesModel> {
    @Override
    public EmployeesModel apply(List<Employee> entity) {
        return EmployeesModel.builder()
                .employees(entity.stream()
                        .map(employee -> EmployeesModel.Employee.builder()
                                .id(employee.getId())
                                .name(employee.getName())
                                .build())
                        .toList())
                .build();

    }
}
