package com.company.hightechcomapny.component;

import com.company.hightechcomapny.employee.dto.function.EmployeeToResponseFunction;
import com.company.hightechcomapny.employee.dto.function.EmployeesToResponseFunction;
import com.company.hightechcomapny.employee.dto.function.RequestToEmployeeFunction;
import com.company.hightechcomapny.employee.dto.function.UpdateEmployeeWithRequestFunction;
import com.company.hightechcomapny.project.dto.function.ProjectToResponseFunction;
import com.company.hightechcomapny.project.dto.function.ProjectsToResponseFunction;
import com.company.hightechcomapny.project.dto.function.RequestToProjectFunction;
import com.company.hightechcomapny.project.dto.function.UpdateProjectWithRequestFunction;
import com.company.hightechcomapny.task.dto.function.RequestToTaskFunction;
import com.company.hightechcomapny.task.dto.function.TaskToResponseFunction;
import com.company.hightechcomapny.task.dto.function.TasksToResponseFunction;
import com.company.hightechcomapny.task.dto.function.UpdateTaskWithRequestFunction;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
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

    public TaskToResponseFunction taskToResponse() {
        return new TaskToResponseFunction();
    }
    public TasksToResponseFunction tasksToResponse() {
        return new TasksToResponseFunction();
    }
    public RequestToTaskFunction requestToTask() {
        return new RequestToTaskFunction();
    }
    public UpdateTaskWithRequestFunction updateTask() {
        return new UpdateTaskWithRequestFunction();
    }

    public ProjectToResponseFunction projectToResponse() {
        return new ProjectToResponseFunction();
    }
    public ProjectsToResponseFunction projectsToResponse() {
        return new ProjectsToResponseFunction();
    }
    public RequestToProjectFunction requestToProject() {
        return new RequestToProjectFunction();
    }
    public UpdateProjectWithRequestFunction updateProject() {
        return new UpdateProjectWithRequestFunction();
    }

}
