package com.company.hightechcomapny.task.view;
import com.company.hightechcomapny.component.ModelFunctionFactory;
import com.company.hightechcomapny.employee.entity.Employee;
import com.company.hightechcomapny.employee.model.EmployeeModel;
import com.company.hightechcomapny.employee.service.EmployeeService;
import com.company.hightechcomapny.project.entity.Project;
import com.company.hightechcomapny.project.model.ProjectModel;
import com.company.hightechcomapny.project.service.ProjectService;
import com.company.hightechcomapny.task.dto.GetTaskResponse;
import com.company.hightechcomapny.task.entity.Priority;
import com.company.hightechcomapny.task.entity.Task;
import com.company.hightechcomapny.task.model.TaskCreateModel;
import com.company.hightechcomapny.task.service.TaskService;
import jakarta.enterprise.context.Conversation;
import jakarta.enterprise.context.ConversationScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//@ViewScoped
@ConversationScoped
@Named
@Log
@NoArgsConstructor(force = true)
public class TaskCreate implements Serializable {
    private final TaskService taskService;
    private final ProjectService projectService;
    private final EmployeeService employeeService;
    private final ModelFunctionFactory factory;
    private final Conversation conversation;

    @Getter
    private TaskCreateModel task;

    @Setter
    @Getter
    private List<ProjectModel> projects;

    @Setter
    @Getter
    private List<EmployeeModel> employees;

    @Getter
    private Priority[] priorities;

//    @Setter
//    @Getter
//    private Employee test;

    private static final UUID TEMP_USER_ID = UUID.fromString("81e1c2a9-7f57-439b-b53d-6db88b07a222");

    @Inject
    public TaskCreate(TaskService taskService, ProjectService projectService, EmployeeService employeeService, ModelFunctionFactory factory, Conversation conversation) {
        this.taskService = taskService;
        this.projectService = projectService;
        this.employeeService = employeeService;
        this.factory = factory;
        this.conversation = conversation;
    }

    public void init() {
        System.out.println("Sieeeeeeeeeeeeeeeeeeeeeeeeeeeeesma");
        if (conversation.isTransient()) {
            Employee tempEmployee = this.employeeService.find(TEMP_USER_ID).get();
            this.projects = projectService.findAll().stream().map(factory.projectToModel()).toList();
            this.priorities = Priority.values();
            this.employees = employeeService.findAll().stream().map(factory.employeeToModel()).toList();
            task = TaskCreateModel.builder()
                    .id(UUID.randomUUID())
                    .employee(tempEmployee)
                    .project(this.projects.get(0))
                    .description("Opis")
                    .build();
            System.out.println("TaskCreate.init called" + task.getProject().getId());
            System.out.println("TaskCreate.init task: " + task);


            conversation.begin();
        }

    }

    public String goToBasicAction(){
        System.out.println("Tutaj1");
        return "/task/task_create__basic.xhtml?faces-redirect=true";
    }
    public String cancelAction() {
        conversation.end();
        return "/task/task_list.xhtml?faces-redirect=true";
    }
    public String goToConfirmAction() {
        task.setDescription("Opis");
        task.setDeadline(LocalDate.now());
//        task.setPriority(Task.HIGH);
        return "/task/task_create__confirm.xhtml?faces-redirect=true";
    }
    public String saveAction() {
        System.out.println("Witam1");
        task.setId(UUID.randomUUID());

        System.out.println("Wybrany projekt: " + task.getProject().getId());
        System.out.println("Wybrany pracownik: " + task.getEmployee().getId());
        System.out.println("Zapisz zadanie: " + task);

        projectService.find(task.getProject().getId()).ifPresent(project -> {
            List<Task> updatedTasks = new ArrayList<>(project.getTasks());
            updatedTasks.add(convertToTask(task));
            project.setTasks(updatedTasks);

            task.setProject(convertToProjectModel(project));
        });


        ProjectModel p = task.getProject();
        task.setProject(null);
        System.out.println("Siema1");
        System.out.println(task);
        taskService.create(factory.modelToTask().apply(task));
        System.out.println("Siema12");
        task.setProject(p);
        System.out.println(task);
        System.out.println(task.getProject());
        System.out.println("Siema2");
        conversation.end();
        taskService.update(convertToTask(task));
        System.out.println("Siema3");
        return "/task/task_list.xhtml?faces-redirect=true";
    }
    public String getConversationId() {
        return conversation.getId();
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

    private Task convertToTask (TaskCreateModel taskCreateModel) {
        if (taskCreateModel == null) {
            return null;
        }
        return Task.builder()
                .id(taskCreateModel.getId())
                .description(taskCreateModel.getDescription())
                .deadline(taskCreateModel.getDeadline())
                .priority(taskCreateModel.getPriority())
                .employee(taskCreateModel.getEmployee())
                .build();
    }



    private Project convertToProject (ProjectModel projectModel) {
        if (projectModel == null) {
            return null;
        }
        return Project.builder()
                .id(projectModel.getId())
                .budget(projectModel.getBudget())
                .name(projectModel.getName())
                .tasks(projectModel.getTasks())
                .build();
    }




}
