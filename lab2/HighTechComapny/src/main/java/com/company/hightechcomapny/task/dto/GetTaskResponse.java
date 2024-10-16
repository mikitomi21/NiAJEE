package com.company.hightechcomapny.task.dto;

import com.company.hightechcomapny.employee.entity.Employee;
import com.company.hightechcomapny.project.entity.Project;
import com.company.hightechcomapny.task.entity.Priority;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetTaskResponse {
    private UUID id;
    private String description;
    private LocalDate deadline;
    private Priority priority;

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Employee {
        private UUID id;
        private String name;
        private Integer salary;

    }

//    @Getter
//    @Setter
//    @Builder
//    @NoArgsConstructor
//    @AllArgsConstructor(access = AccessLevel.PRIVATE)
//    @ToString
//    @EqualsAndHashCode
//    public static class Project {
//        private UUID id;
//        private String name;
//        private Integer budget;
//
//    }
}
