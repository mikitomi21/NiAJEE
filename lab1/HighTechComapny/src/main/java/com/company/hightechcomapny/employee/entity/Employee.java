package com.company.hightechcomapny.employee.entity;
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
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode

public class Employee {
    private UUID id;
    private String name;
    private Integer salary;

    private List<Task> tasks;

//    @ToString.Exclude
//    private String password;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private byte[] picture;

}
