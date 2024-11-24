package com.company.hightechcomapny.employee.view;

import com.company.hightechcomapny.component.ModelFunctionFactory;
import com.company.hightechcomapny.employee.model.EmployeeModel;
import com.company.hightechcomapny.employee.model.EmployeesModel;
import com.company.hightechcomapny.employee.service.EmployeeService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import lombok.NoArgsConstructor;

@RequestScoped
@Named
@NoArgsConstructor(force = true)
public class EmployeeList {
    private final EmployeeService service;
    private EmployeesModel employees;

    private final ModelFunctionFactory factory;

    public EmployeeList(EmployeeService service, ModelFunctionFactory factory) {
        this.service = service;
        this.factory = factory;
    }
    public EmployeesModel getEmployee() {
        if (employees == null) {
            employees = factory.employeesToModel().apply(service.findAll());
        }
        return employees;
    }

    public String deleteAction(EmployeesModel.Employee employee) {
        service.delete(employee.getId());
        return "employee_list?faces-redirect=true";
    }

}
