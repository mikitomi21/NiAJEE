package com.company.hightechcomapny.project.dto.function;

import com.company.hightechcomapny.project.dto.GetProjectsResponse;
import com.company.hightechcomapny.project.entity.Project;

import java.util.List;
import java.util.function.Function;

public class ProjectsToResponseFunction implements Function<List<Project>, GetProjectsResponse> {
    @Override
    public GetProjectsResponse apply(List<Project> projects) {
        return GetProjectsResponse.builder()
                .projects(projects.stream()
                        .map(project -> GetProjectsResponse.Project.builder()
                                .id(project.getId())
                                .name(project.getName())
                                .budget(project.getBudget())
                                .build())
                        .toList())
                .build();
    }
}
