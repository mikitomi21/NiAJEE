package com.company.hightechcomapny.employee.dto;

import com.company.hightechcomapny.task.entity.Task;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PutEmployeeRequest {
    private UUID id;
    private String name;
    private Integer salary;

    @Singular
    private List<Task> tasks;
}
