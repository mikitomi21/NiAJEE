package com.company.hightechcomapny.employee.dto.function;

import com.company.hightechcomapny.employee.dto.PutEmployeeRequest;
import com.company.hightechcomapny.employee.entity.Employee;

import java.util.UUID;
import java.util.function.BiFunction;

public class RequestToEmployeeFunction implements BiFunction<UUID, PutEmployeeRequest, Employee> {
    @Override
    public Employee apply(UUID uuid, PutEmployeeRequest putEmployeeRequest) {
        return Employee.builder()
                .id(uuid)
                .name(putEmployeeRequest.getName())
                .salary(putEmployeeRequest.getSalary())
                .build();
    }
}
