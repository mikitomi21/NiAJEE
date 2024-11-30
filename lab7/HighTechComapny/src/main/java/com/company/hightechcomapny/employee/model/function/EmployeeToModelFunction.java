package com.company.hightechcomapny.employee.model.function;

import com.company.hightechcomapny.employee.entity.Employee;
import com.company.hightechcomapny.employee.model.EmployeeModel;

import java.io.Serializable;
import java.util.function.Function;

public class EmployeeToModelFunction implements Function<Employee, EmployeeModel>, Serializable {
    @Override
    public EmployeeModel apply(Employee employee) {
        return EmployeeModel.builder()
                .id(employee.getId())
                .name(employee.getName())
//                .salary(employee.getSalary())
                .build();
    }
}
