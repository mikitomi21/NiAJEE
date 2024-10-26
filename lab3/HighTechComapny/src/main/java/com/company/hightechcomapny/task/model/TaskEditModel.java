package com.company.hightechcomapny.task.model;

import java.time.LocalDate;

import com.company.hightechcomapny.employee.entity.Employee;
import com.company.hightechcomapny.employee.model.EmployeeModel;
import com.company.hightechcomapny.project.entity.Project;
import com.company.hightechcomapny.project.model.ProjectModel;
import com.company.hightechcomapny.project.model.ProjectsModel;
import com.company.hightechcomapny.task.entity.Priority;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class TaskEditModel {
    private String description;
    private LocalDate deadline;
    private Priority priority;

    private EmployeeModel employee;
    private ProjectModel project;
}
