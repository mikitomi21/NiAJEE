package com.company.hightechcomapny.project.model.converter;

import com.company.hightechcomapny.component.ModelFunctionFactory;
import com.company.hightechcomapny.project.entity.Project;
import com.company.hightechcomapny.project.model.ProjectModel;
import com.company.hightechcomapny.project.service.ProjectService;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;

import java.util.Optional;
import java.util.UUID;

@FacesConverter(value = "projectConverter", forClass = ProjectModel.class, managed = true)
public class ProjectModelConverter implements Converter<ProjectModel> {
    private final ProjectService service;
    private final ModelFunctionFactory factory;

    @Inject
    public ProjectModelConverter(ProjectService service, ModelFunctionFactory factory) {
        this.service = service;
        this.factory = factory;
    }

    @Override
    public ProjectModel getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        Optional<Project> project = service.find(UUID.fromString(value));
        return project.map(factory.projectToModel()).orElse(null);

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, ProjectModel value) {
        System.out.println("ProjectModelConverter.getAsString called");
        return value == null ? "" : value.getId().toString();
    }
}
