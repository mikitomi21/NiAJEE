package com.company.hightechcomapny.configuration.listener;

import com.company.hightechcomapny.datastore.DataStore;
import com.company.hightechcomapny.employee.entity.Employee;
import com.company.hightechcomapny.employee.repository.api.EmployeeRepository;
import com.company.hightechcomapny.employee.repository.memory.EmployeeInMemoryRepository;
import com.company.hightechcomapny.employee.service.EmployeeService;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class CreateServices implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        System.out.println("Creating services");
        DataStore dataSource = (DataStore) event.getServletContext().getAttribute("datasource");

        EmployeeRepository employeeRepository = new EmployeeInMemoryRepository(dataSource);

        event.getServletContext().setAttribute("employeeService", new EmployeeService(employeeRepository));
    }

}
