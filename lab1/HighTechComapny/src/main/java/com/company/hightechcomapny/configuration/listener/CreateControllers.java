package com.company.hightechcomapny.configuration.listener;

import com.company.hightechcomapny.component.DtoFunctionFactory;
import com.company.hightechcomapny.employee.controller.simple.EmployeeSimpleController;
import com.company.hightechcomapny.employee.entity.Employee;
import com.company.hightechcomapny.employee.service.EmployeeService;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class CreateControllers implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        EmployeeService employeeService = (EmployeeService) event.getServletContext().getAttribute("employeeService");
        event.getServletContext().setAttribute("employeeController", new EmployeeSimpleController(
                employeeService,
                new DtoFunctionFactory()
        ));
    }

}
