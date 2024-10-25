package com.company.hightechcomapny.task.model;
import com.company.hightechcomapny.employee.entity.Employee;
import com.company.hightechcomapny.project.entity.Project;
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

public class TaskModel {
    private String description;
    private LocalDate deadline;
    private Priority priority;

//    private Employee employee;
    private String project;
}
