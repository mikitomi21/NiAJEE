package com.company.hightechcomapny.project.dto.function;

import com.company.hightechcomapny.project.dto.PutProjectRequest;
import com.company.hightechcomapny.project.entity.Project;

import java.util.UUID;
import java.util.function.BiFunction;

public class RequestToProjectFunction implements BiFunction<UUID, PutProjectRequest, Project> {
    @Override
    public Project apply(UUID uuid, PutProjectRequest putProjectRequest) {
        return Project.builder()
                .id(uuid)
                .name(putProjectRequest.getName())
                .budget(putProjectRequest.getBudget())
                .build();
    }
}
