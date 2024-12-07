package com.company.hightechcomapny.project.dto.function;

import com.company.hightechcomapny.employee.dto.GetEmployeeResponse;
import com.company.hightechcomapny.employee.entity.Employee;
import com.company.hightechcomapny.project.dto.GetProjectResponse;
import com.company.hightechcomapny.project.entity.Project;

import java.util.function.Function;

public class ProjectToResponseFunction implements Function<Project, GetProjectResponse> {
    @Override
    public GetProjectResponse apply(Project project) {
        return GetProjectResponse.builder()
                .id(project.getId())
                .name(project.getName())
                .budget(project.getBudget())
                .build();
    }
}
