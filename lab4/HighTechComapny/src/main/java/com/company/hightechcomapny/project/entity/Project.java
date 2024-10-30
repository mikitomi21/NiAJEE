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
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Project {
    private UUID id;
    private String name;
    private Integer budget;

    @ToString.Exclude
    private List<Task> tasks;  // w klasie Project

    @Override
    public String toString() {
        return "Project{id=" + id + ", name='" + name + "', budget=" + budget + "}";
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(id, project.id);
    }
}
