package com.company.hightechcomapny.project.controller.api;

import com.company.hightechcomapny.project.dto.GetProjectResponse;
import com.company.hightechcomapny.project.dto.GetProjectsResponse;
import com.company.hightechcomapny.project.dto.PatchProjectRequest;
import com.company.hightechcomapny.project.dto.PutProjectRequest;

import java.util.UUID;

public interface ProjectController {
    GetProjectResponse getProject(UUID id);
    GetProjectsResponse getProjects();
    void putProject(UUID id, PutProjectRequest request);
    void patchProject(UUID id, PatchProjectRequest request);
    void deleteProject(UUID id);
}
