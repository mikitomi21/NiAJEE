package com.company.hightechcomapny.task.view;
import com.company.hightechcomapny.component.ModelFunctionFactory;
import com.company.hightechcomapny.task.model.TaskCreateModel;
import com.company.hightechcomapny.task.service.TaskService;
import jakarta.enterprise.context.Conversation;
import jakarta.enterprise.context.ConversationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@ConversationScoped
@Named
@Log
@NoArgsConstructor(force = true)
public class TaskCreate implements Serializable {
    private final TaskService taskService;
    private final ModelFunctionFactory factory;
    private final Conversation conversation;

    @Getter
    private TaskCreateModel task;

    @Inject
    public TaskCreate(TaskService taskService, ModelFunctionFactory factory, Conversation conversation) {
        this.taskService = taskService;
        this.factory = factory;
        this.conversation = conversation;
    }

    public void init() {
        if (conversation.isTransient()) {
            task = TaskCreateModel.builder()
                    .id(UUID.randomUUID())
                    .build();
            conversation.begin();
        }
    }

    public String goToBasicAction(){
        return "/task/task_create__basic.xhtml?faces-redirect=true";
    }
    public String cancelAction() {
        conversation.end();
        return "/task/task_list.xhtml?faces-redirect=true";
    }
    public String goToConfirmAction() {
        task.setDescription("Opis");
        task.setDeadline(LocalDate.now());
//        task.setPriority(Task.HIGH);
        return "/task/task_create__confirm.xhtml?faces-redirect=true";
    }
    public String saveAction() {
        taskService.create(factory.modelToTask().apply(task));
        conversation.end();
        return "/task/task_list.xhtml?faces-redirect=true";
    }
    public String getConversationId() {
        return conversation.getId();
    }




}
