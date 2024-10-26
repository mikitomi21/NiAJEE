package com.company.hightechcomapny.task.model;
import com.company.hightechcomapny.employee.entity.Employee;
import com.company.hightechcomapny.employee.model.EmployeeModel;
import com.company.hightechcomapny.project.entity.Project;
import com.company.hightechcomapny.project.model.ProjectModel;
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
public class TaskCreateModel {
    private UUID id;
    private String description;
    private LocalDate deadline;
    private Priority priority;

    private Employee employee;
    private ProjectModel project;
}
