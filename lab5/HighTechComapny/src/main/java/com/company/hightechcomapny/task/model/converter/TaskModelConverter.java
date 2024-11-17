package com.company.hightechcomapny.task.model.converter;

import com.company.hightechcomapny.component.ModelFunctionFactory;
import com.company.hightechcomapny.task.entity.Task;
import com.company.hightechcomapny.task.model.TaskModel;
import com.company.hightechcomapny.task.service.TaskService;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;

import java.util.Optional;
import java.util.UUID;

@FacesConverter(forClass = TaskModel.class, managed = true)
public class TaskModelConverter implements Converter<TaskModel> {
    private final TaskService service;
    private final ModelFunctionFactory factory;

    @Inject
    public TaskModelConverter(TaskService service, ModelFunctionFactory factory) {
        this.service = service;
        this.factory = factory;
    }

    @Override
    public TaskModel getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        Optional<Task> task = service.find(UUID.fromString(value));
        return task.map(factory.taskToModel()).orElse(null);

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, TaskModel value) {
        System.out.println("TaskModelConverter.getAsString called");
        return value == null ? "" : value.getId().toString();
    }
}
