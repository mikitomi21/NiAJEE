package com.company.hightechcomapny.configuration.singleton;

import com.company.hightechcomapny.employee.entity.Employee;
import com.company.hightechcomapny.employee.entity.EmployeeRoles;
import com.company.hightechcomapny.employee.service.EmployeeService;
import com.company.hightechcomapny.project.entity.Project;
import com.company.hightechcomapny.project.service.ProjectService;
import com.company.hightechcomapny.task.entity.Priority;
import com.company.hightechcomapny.task.entity.Task;
import com.company.hightechcomapny.task.service.TaskService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

import jakarta.enterprise.context.control.RequestContextController;

import jakarta.servlet.ServletContextListener;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Singleton
@Startup
@TransactionAttribute(value = TransactionAttributeType.NOT_SUPPORTED)
@NoArgsConstructor
public class InitializedData implements ServletContextListener  {

    private EmployeeService employeeService;
    private TaskService taskService;
    private ProjectService projectService;

    @EJB
    public void setEmployeeService(EmployeeService employeeService){
        this.employeeService = employeeService;
    }
    @EJB
    public void setTaskService(TaskService taskService){
        this.taskService = taskService;
    }
    @EJB
    public void setProjectService(ProjectService projectService){
        this.projectService = projectService;
    }


//    public void contextInitialized(@Observes @Initialized(ApplicationScoped.class) Object init) {
//        init();
//    }


    @PostConstruct
    @SneakyThrows
    private void init() {
        // TODO tutaj sprawdzenie cos o adminie
        // Tworzenie pracowników
        Employee emp1 = Employee.builder()
                .id(UUID.fromString("81e1c2a9-7f57-439b-b53d-6db88b07a000"))
                .name("Jakub")
                .salary(10000)
                .picture("/images/jakub.jpg")
                .tasks(Collections.emptyList())
                .password("siema")
                .roles(List.of(EmployeeRoles.ADMIN))
                .build();
        Employee emp2 = Employee.builder()
                .id(UUID.fromString("81e1c2a9-7f57-439b-b53d-6db88b07a111"))
                .name("Michal")
                .salary(35000)
                .picture("/images/michal.jpg")
                .tasks(Collections.emptyList())
                .password("siema")
                .roles(List.of(EmployeeRoles.ADMIN))
                .build();
        Employee emp3 = Employee.builder()
                .id(UUID.fromString("81e1c2a9-7f57-439b-b53d-6db88b07a222"))
                .name("Katarzyna")
                .salary(200)
                .picture("/images/katarzyna.jpg")
                .tasks(Collections.emptyList())
                .password("siema")
                .roles(List.of(EmployeeRoles.ADMIN))
                .build();
        Employee emp4 = Employee.builder()
                .id(UUID.fromString("81e1c2a9-7f57-439b-b53d-6db88b07a333"))
                .name("Basia")
                .salary(6969)
                .picture("/images/basia.jpg")
                .tasks(Collections.emptyList())
                .password("siema")
                .roles(List.of(EmployeeRoles.ADMIN))
                .build();

        // Tworzenie projektów
        Project pr1 = Project.builder()
                .id(UUID.fromString("5551c2a9-7f57-439b-b53d-6db88b07a000"))
                .name("Web API")
                .budget(100000)
                .tasks(Collections.emptyList())
                .build();
        Project pr2 = Project.builder()
                .id(UUID.fromString("5551c2a9-7f57-439b-b53d-6db88b07a111"))
                .name("Scrapper")
                .budget(3000)
                .tasks(Collections.emptyList())
                .build();

        // Tworzenie zadań i powiązanie z projektami oraz pracownikami
        Task tsk1 = Task.builder()
                .id(UUID.fromString("0001c2a9-7f57-439b-b53d-6db88b07a000"))
                .description("Add new features")
                .deadline(LocalDate.of(2024, 12, 11))
                .priority(Priority.MEDIUM)
                .project(null)
                .employee(emp1)
                .build();
        Task tsk2 = Task.builder()
                .id(UUID.fromString("0001c2a9-7f57-439b-b53d-6db88b07a111"))
                .description("Delete old features")
                .deadline(LocalDate.of(2024, 10, 10))
                .priority(Priority.HIGH)
                .project(null)
                .employee(emp2)
                .build();

        employeeService.create(emp1);
        employeeService.create(emp2);
        employeeService.create(emp3);
        employeeService.create(emp4);

        taskService.create(tsk1);
        taskService.create(tsk2);

        projectService.create(pr1);
        projectService.create(pr2);

//          Update
        tsk1.setProject(pr1);
        tsk2.setProject(pr2);

        taskService.update(tsk1);
        taskService.update(tsk2);

        pr1.setTasks(List.of(tsk1));
        pr2.setTasks(List.of(tsk2));

        projectService.update(pr1);
        projectService.update(pr2);

        emp1.setTasks(List.of(tsk1));
        emp2.setTasks(List.of(tsk2));

        System.out.println(emp1.getRoles());
        System.out.println("Siema");
        employeeService.update(emp1);
        employeeService.update(emp2);


        System.out.println("Dane inicjalizacyjne zostały pomyślnie zapisane do bazy H2.");
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
