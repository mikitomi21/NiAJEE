package com.company.hightechcomapny.task.dto.function;

import com.company.hightechcomapny.employee.dto.GetEmployeeResponse;
import com.company.hightechcomapny.employee.entity.Employee;
import com.company.hightechcomapny.task.dto.GetTaskResponse;
import com.company.hightechcomapny.task.entity.Task;

import java.util.function.Function;

public class TaskToResponseFunction implements Function<Task, GetTaskResponse>  {
    @Override
    public GetTaskResponse apply(Task task) {
        return GetTaskResponse.builder()
                .id(task.getId())
                .description(task.getDescription())
                .deadline(task.getDeadline())
                .priority(task.getPriority())
                .build();
    }
}
