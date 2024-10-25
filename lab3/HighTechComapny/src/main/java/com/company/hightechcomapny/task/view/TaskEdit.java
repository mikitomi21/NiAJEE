package com.company.hightechcomapny.task.view;
import com.company.hightechcomapny.component.ModelFunctionFactory;
import com.company.hightechcomapny.task.entity.Task;
import com.company.hightechcomapny.task.model.TaskEditModel;
import com.company.hightechcomapny.task.service.TaskService;
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

/**
 * View bean for rendering single character edit form.
 */
@ViewScoped
@Named
public class TaskEdit implements Serializable {
    private final TaskService service;
    private final ModelFunctionFactory factory;

    @Inject
    public TaskEdit(TaskService service, ModelFunctionFactory factory) {
        this.service = service;
        this.factory = factory;
    }

    @Setter
    @Getter
    private UUID id;

    @Getter
    private TaskEditModel task;

    public void init() throws IOException {
        Optional<Task> task = service.find(id);
        if (task.isPresent()) {
            this.task = factory.taskToEditModel().apply(task.get());
        } else {
            FacesContext.getCurrentInstance().getExternalContext().responseSendError(HttpServletResponse.SC_NOT_FOUND, "Character not found");
        }
    }
    public String saveAction() {
        service.update(factory.updateTask().apply(service.find(id).orElseThrow(), task));
        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        return viewId + "?faces-redirect=true&includeViewParams=true";
    }

}
