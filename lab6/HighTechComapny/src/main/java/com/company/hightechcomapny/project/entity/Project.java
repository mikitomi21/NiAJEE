package com.company.hightechcomapny.project.entity;

import com.company.hightechcomapny.employee.entity.Employee;
import com.company.hightechcomapny.task.entity.Task;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "projects")
public class Project {
    @Id
    private UUID id;
    private String name;
    private Integer budget;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "project", cascade = CascadeType.PERSIST)
    private List<Task> tasks;

}
