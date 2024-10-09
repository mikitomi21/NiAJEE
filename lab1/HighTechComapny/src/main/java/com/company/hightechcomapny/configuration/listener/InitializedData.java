package com.company.hightechcomapny.configuration.listener;

import com.company.hightechcomapny.employee.entity.Employee;
import com.company.hightechcomapny.employee.service.EmployeeService;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import lombok.SneakyThrows;

import java.io.InputStream;
import java.util.UUID;

public class InitializedData implements ServletContextListener  {

    private EmployeeService employeeService;
//    private TaskService taskService;
//    private ProjectService projectService;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        employeeService = (EmployeeService) event.getServletContext().getAttribute("characterService");
//        taskService = (TaskService) event.getServletContext().getAttribute("userService");
//        projectService = (ProjectService) event.getServletContext().getAttribute("professionService");
        init();
    }

    @SneakyThrows
    private void init() {
        Employee emp1 = Employee.builder()
                .id(UUID.fromString("81e1c2a9-7f57-439b-b53d-6db88b07m000"))
                .name("Jakub")
                .salary(10000)
                .picture(getResourceAsByteArray("/images/jakub.jpg"))
                .build();
        Employee emp2 = Employee.builder()
                .id(UUID.fromString("81e1c2a9-7f57-439b-b53d-6db88b07m111"))
                .name("Michał")
                .salary(35000)
                .picture(getResourceAsByteArray("/images/michal.jpg"))
                .build();
        Employee emp3 = Employee.builder()
                .id(UUID.fromString("81e1c2a9-7f57-439b-b53d-6db88b07m222"))
                .name("Katarzyna")
                .salary(200)
                .picture(getResourceAsByteArray("/images/katarzyna.jpg"))
                .build();
        Employee emp4 = Employee.builder()
                .id(UUID.fromString("81e1c2a9-7f57-439b-b53d-6db88b07m333"))
                .name("Basia")
                .salary(6969)
                .picture(getResourceAsByteArray("/images/basia.jpg"))
                .build();

        employeeService.create(emp1);
        employeeService.create(emp2);
        employeeService.create(emp3);
        employeeService.create(emp4);
    }

    @SneakyThrows
    private byte[] getResourceAsByteArray(String path) {
        try (InputStream is = this.getClass().getResourceAsStream(path)) {
            if (is != null) {
                return is.readAllBytes();
            } else {
                throw new IllegalStateException("Unable to get resource %s".formatted(path));
            }
        }
    }



}
