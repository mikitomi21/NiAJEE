package com.company.hightechcomapny.project.model.function;

import com.company.hightechcomapny.project.entity.Project;
import com.company.hightechcomapny.project.model.ProjectModel;

import java.io.Serializable;
import java.util.function.Function;

public class ProjectToModelFunction implements Function<Project, ProjectModel>, Serializable {
    @Override
    public ProjectModel apply(Project project) {
        return ProjectModel.builder()
                .id(project.getId())
                .name(project.getName())
                .budget(project.getBudget())
                .tasks(project.getTasks())
                .build();
    }
}
