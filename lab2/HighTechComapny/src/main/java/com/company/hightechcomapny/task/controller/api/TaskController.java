package com.company.hightechcomapny.task.controller.api;

import com.company.hightechcomapny.task.dto.GetTaskResponse;
import com.company.hightechcomapny.task.dto.GetTasksResponse;
import com.company.hightechcomapny.task.dto.PatchTaskRequest;
import com.company.hightechcomapny.task.dto.PutTaskRequest;

import java.util.UUID;

public interface TaskController {
    GetTaskResponse getTask(UUID id);
    GetTasksResponse getTasks();
    void putTask(UUID id, PutTaskRequest request);

    void patchTask(UUID id, PatchTaskRequest request);

    void deleteTask(UUID id);
}
