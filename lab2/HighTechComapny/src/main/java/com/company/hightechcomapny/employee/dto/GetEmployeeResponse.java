package com.company.hightechcomapny.employee.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode

public class GetEmployeeResponse {
//    @Getter
//    @Setter
//    @Builder
//    @NoArgsConstructor
//    @AllArgsConstructor(access = AccessLevel.PRIVATE)
//    @ToString
//    @EqualsAndHashCode
//
//    public static class Task {
//        private UUID id;
//        private String description;
//
//    }
    private UUID id;
    private String name;
    private Integer salary;

//    @Singular
//    private List<Task> tasks;
}
