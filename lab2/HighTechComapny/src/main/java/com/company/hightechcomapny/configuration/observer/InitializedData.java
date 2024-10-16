package com.company.hightechcomapny.configuration.observer;

import com.company.hightechcomapny.employee.entity.Employee;
import com.company.hightechcomapny.employee.service.EmployeeService;
import com.company.hightechcomapny.project.entity.Project;
import com.company.hightechcomapny.project.service.ProjectService;
import com.company.hightechcomapny.task.entity.Priority;
import com.company.hightechcomapny.task.entity.Task;
import com.company.hightechcomapny.task.service.TaskService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

import jakarta.enterprise.context.control.RequestContextController;

import jakarta.servlet.ServletContextListener;
import lombok.SneakyThrows;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@ApplicationScoped
public class InitializedData implements ServletContextListener  {

    private EmployeeService employeeService;
    private TaskService taskService;
    private ProjectService projectService;

    private final RequestContextController requestContextController;
    @Inject
    public InitializedData(EmployeeService employeeService, TaskService taskService, ProjectService projectService, RequestContextController requestContextController) {
        this.employeeService = employeeService;
        this.taskService = taskService;
        this.projectService = projectService;
        this.requestContextController = requestContextController;
    }

    public void contextInitialized(@Observes @Initialized(ApplicationScoped.class) Object init) {
        init();
    }


    @SneakyThrows
    private void init() {
        requestContextController.activate();

        Task tsk1 = Task.builder()
                .id(UUID.fromString("0001c2a9-7f57-439b-b53d-6db88b07a000"))
                .description("Add new features")
                .deadline(LocalDate.of(2024, 12, 11))
                .priority(Priority.MEDIUM)
                .build();
        Task tsk2 = Task.builder()
                .id(UUID.fromString("0001c2a9-7f57-439b-b53d-6db88b07a111"))
                .description("Delete old features")
                .deadline(LocalDate.of(2024, 10, 10))
                .priority(Priority.HIGH)
                .build();
        taskService.create(tsk1);
        taskService.create(tsk2);

        Employee emp1 = Employee.builder()
                .id(UUID.fromString("81e1c2a9-7f57-439b-b53d-6db88b07a000"))
                .name("Jakub")
                .salary(10000)
                .picture("/images/jakub.jpg")
                .build();
        Employee emp2 = Employee.builder()
                .id(UUID.fromString("81e1c2a9-7f57-439b-b53d-6db88b07a111"))
                .name("Michal")
                .salary(35000)
                .picture("/images/michal.jpg")
                .build();
        Employee emp3 = Employee.builder()
                .id(UUID.fromString("81e1c2a9-7f57-439b-b53d-6db88b07a222"))
                .name("Katarzyna")
                .salary(200)
                .picture("/images/katarzyna.jpg")
                .build();
        Employee emp4 = Employee.builder()
                .id(UUID.fromString("81e1c2a9-7f57-439b-b53d-6db88b07a333"))
                .name("Basia")
                .salary(6969)
                .picture("/images/basia.jpg")
                .build();

        employeeService.create(emp1);
        employeeService.create(emp2);
        employeeService.create(emp3);
        employeeService.create(emp4);

        Project pr1 = Project.builder()
                .id(UUID.fromString("5551c2a9-7f57-439b-b53d-6db88b07a000"))
                .name("Web API")
                .budget(100000)
                .build();
        Project pr2 = Project.builder()
                .id(UUID.fromString("5551c2a9-7f57-439b-b53d-6db88b07a111"))
                .name("Scrapper")
                .budget(3000)
                .build();

        projectService.create(pr1);
        projectService.create(pr2);

        requestContextController.deactivate();
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
