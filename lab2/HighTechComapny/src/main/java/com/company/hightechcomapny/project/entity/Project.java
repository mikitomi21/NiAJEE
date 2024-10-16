package com.company.hightechcomapny.project.entity;

import com.company.hightechcomapny.employee.entity.Employee;
import com.company.hightechcomapny.task.entity.Task;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class Project {
    private UUID id;
    private String name;
    private Integer budget;

    private List<Task> tasks;
}
