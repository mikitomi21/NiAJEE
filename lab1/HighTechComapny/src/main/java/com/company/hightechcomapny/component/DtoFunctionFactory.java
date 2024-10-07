package com.company.hightechcomapny.component;

import com.company.hightechcomapny.employee.dto.function.EmployeeToResponseFunction;
import com.company.hightechcomapny.employee.dto.function.EmployeesToResponseFunction;
import com.company.hightechcomapny.employee.dto.function.RequestToEmployeeFunction;
import com.company.hightechcomapny.employee.dto.function.UpdateEmployeeWithRequestFunction;
import org.hibernate.sql.Update;

public class DtoFunctionFactory {
    public EmployeeToResponseFunction employeeToResponse() {
        return new EmployeeToResponseFunction();
    }

    public EmployeesToResponseFunction employeesToResponse() {
        return new EmployeesToResponseFunction();
    }

    public RequestToEmployeeFunction requestToEmployee() {
        return new RequestToEmployeeFunction();
    }

    public UpdateEmployeeWithRequestFunction updateEmployee() {
        return new UpdateEmployeeWithRequestFunction();
    }

}
