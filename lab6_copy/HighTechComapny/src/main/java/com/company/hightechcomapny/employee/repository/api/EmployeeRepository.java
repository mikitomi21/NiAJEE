package com.company.hightechcomapny.employee.repository.api;

import com.company.hightechcomapny.employee.entity.Employee;
import com.company.hightechcomapny.repository.api.Repository;

import java.util.Optional;
import java.util.UUID;

public interface EmployeeRepository extends Repository<Employee, UUID> {
    Optional<Employee> findByName(String name);
}
