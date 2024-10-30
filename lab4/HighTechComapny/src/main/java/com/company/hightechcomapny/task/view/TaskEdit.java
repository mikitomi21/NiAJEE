package com.company.hightechcomapny.task.view;
import com.company.hightechcomapny.component.ModelFunctionFactory;
import com.company.hightechcomapny.employee.entity.Employee;
import com.company.hightechcomapny.employee.model.EmployeeModel;
import com.company.hightechcomapny.employee.service.EmployeeService;
import com.company.hightechcomapny.project.entity.Project;
import com.company.hightechcomapny.project.model.ProjectModel;
import com.company.hightechcomapny.project.service.ProjectService;
import com.company.hightechcomapny.task.entity.Priority;
import com.company.hightechcomapny.task.entity.Task;
import com.company.hightechcomapny.task.model.TaskEditModel;
import com.company.hightechcomapny.task.service.TaskService;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * View bean for rendering single character edit form.
 */
@ViewScoped
@Named
public class TaskEdit implements Serializable {
    private final TaskService service;
    private final EmployeeService employeeService;
    private final ProjectService projectService;
    private final ModelFunctionFactory factory;

    @Inject
    public TaskEdit(TaskService service, EmployeeService employeeService, ProjectService projectService, ModelFunctionFactory factory) {
        this.service = service;
        this.employeeService = employeeService;
        this.projectService = projectService;
        this.factory = factory;
    }

    @Setter
    @Getter
    private UUID id;

    @Getter
    private TaskEditModel task;

    @Setter
    @Getter
    private List<ProjectModel> projects;

    @Setter
    @Getter
    private List<EmployeeModel> employees;

    @Getter
    private Priority[] priorities;

    public void init() throws IOException {
        System.out.println("TaskEdit.init called");
        Optional<Task> task = service.find(id);
        if (task.isPresent()) {
            System.out.println("-----------------------------------------");
            System.out.println("TaskEdit.init task: " + task);
            System.out.println("TaskEdit.init task.isPresent");
            this.task = factory.taskToEditModel().apply(task.get());
            System.out.println("TaskEdit.init task: " + this.task);
            this.projects = projectService.findAll().stream().map(factory.projectToModel()).toList();
            System.out.println("TaskEdit.init projects: " + this.projects);
            this.employees = employeeService.findAll().stream().map(factory.employeeToModel()).toList();
            System.out.println("TaskEdit.init employees: " + this.employees);
            this.priorities = Priority.values();
        } else {
            FacesContext.getCurrentInstance().getExternalContext().responseSendError(HttpServletResponse.SC_NOT_FOUND, "Character not found");
        }
    }
    public String saveAction() {
        Employee employee = employeeService.find(task.getEmployee().getId()).orElseThrow();
        Project project = projectService.find(task.getProject().getId()).orElseThrow();

        // Przypisz referencje do task
        task.setEmployee(convertToEmployeeModel(employee));
        task.setProject(convertToProjectModel(project));
        System.out.println("TaskEdit.saveAction");
        System.out.println("==============================================================================");
        System.out.println("TaskEdit.saveAction task: " + task);
        service.update(factory.updateTask().apply(service.find(id).orElseThrow(), task));
        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        return viewId + "?faces-redirect=true&includeViewParams=true";
    }

//    public List<Employee> getEmployees() {
//        return employeeService.findAll();
//    }

//    public List<Project> getProjects() {
//        return projectService.findAll();
//    }
//

    private EmployeeModel convertToEmployeeModel(Employee employee) {
        if (employee == null) {
            return null;
        }
        return EmployeeModel.builder()
                .id(employee.getId())
                .name(employee.getName())
                .build();
    }

    private ProjectModel convertToProjectModel(Project project) {
        if (project == null) {
            return null;
        }
        return ProjectModel.builder()
                .id(project.getId())
                .name(project.getName())
                .build();
    }
}
