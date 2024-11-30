package com.company.hightechcomapny.project.dto;

import com.company.hightechcomapny.employee.dto.GetEmployeesResponse;
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
public class GetProjectsResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Project {
        private UUID id;
        private String name;
        private Integer budget;
    }

    @Singular
    private List<Project> projects;
}
