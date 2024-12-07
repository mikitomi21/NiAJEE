package com.company.hightechcomapny.task.view;

import com.company.hightechcomapny.component.ModelFunctionFactory;
import com.company.hightechcomapny.task.entity.Task;
import com.company.hightechcomapny.task.model.TaskModel;
import com.company.hightechcomapny.task.service.TaskService;
import jakarta.ejb.EJB;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

@ViewScoped
@Named
public class TaskView implements Serializable {
    private TaskService service;
    private final ModelFunctionFactory factory;

    @Inject
    public TaskView(ModelFunctionFactory factory) {
        this.factory = factory;
    }

    @EJB
    public void setTaskService(TaskService service){
        this.service = service;
    }

    @Setter
    @Getter
    private UUID id;

    @Getter
    private TaskModel task;

    public void init() throws IOException {
        Optional<Task> task = service.find(id);
        if (task.isPresent()) {
            this.task = factory.taskToModel().apply(task.get());
        } else {
            FacesContext.getCurrentInstance().getExternalContext().responseSendError(HttpServletResponse.SC_NOT_FOUND, "Character not found");
        }

    }
}
