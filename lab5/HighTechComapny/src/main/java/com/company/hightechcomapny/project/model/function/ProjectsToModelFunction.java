package com.company.hightechcomapny.project.model.function;

import com.company.hightechcomapny.project.entity.Project;
import com.company.hightechcomapny.project.model.ProjectsModel;

import java.util.List;
import java.util.function.Function;

public class ProjectsToModelFunction implements Function<List<Project>, ProjectsModel> {
    @Override
    public ProjectsModel apply(List<Project> projects) {
        return ProjectsModel.builder()
                .projects(projects.stream()
                        .map(project -> ProjectsModel.Project.builder()
                                .id(project.getId())
                                .name(project.getName())
                                .build())
                        .toList())
                .build();
    }
}
