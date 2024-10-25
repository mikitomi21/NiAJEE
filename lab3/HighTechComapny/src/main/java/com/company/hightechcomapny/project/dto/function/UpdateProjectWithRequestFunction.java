package com.company.hightechcomapny.project.dto.function;

import com.company.hightechcomapny.employee.dto.PatchEmployeeRequest;
import com.company.hightechcomapny.employee.entity.Employee;
import com.company.hightechcomapny.project.dto.PatchProjectRequest;
import com.company.hightechcomapny.project.entity.Project;

import java.util.function.BiFunction;

public class UpdateProjectWithRequestFunction implements BiFunction<Project, PatchProjectRequest, Project> {
    @Override
    public Project apply(Project project, PatchProjectRequest patchProjectRequest) {
        return Project.builder()
                .id(project.getId())
                .name(patchProjectRequest.getName())
                .budget(patchProjectRequest.getBudget())
                .build();
    }
}
