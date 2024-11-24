package com.company.hightechcomapny.project.repository.api;

import com.company.hightechcomapny.employee.entity.Employee;
import com.company.hightechcomapny.project.entity.Project;
import com.company.hightechcomapny.repository.api.Repository;

import java.util.Optional;
import java.util.UUID;

public interface ProjectRepository  extends Repository<Project, UUID> {
    Optional<Project> findByName(String name);
}
