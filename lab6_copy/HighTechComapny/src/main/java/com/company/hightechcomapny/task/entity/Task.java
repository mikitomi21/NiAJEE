package com.company.hightechcomapny.task.entity;

import com.company.hightechcomapny.employee.entity.Employee;
import com.company.hightechcomapny.project.entity.Project;
import jakarta.persistence.*;
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
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(callSuper = true)
@EqualsAndHashCode
@Entity
@Table(name = "tasks")
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Task {
    @Id
    private UUID id;
    private String description;
    private LocalDate deadline;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @ManyToOne
    @JoinColumn(name = "employee_name")
    private Employee employee;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "project")
//    @ToString.Exclude
    private Project project;
}
