package com.company.hightechcomapny.employee.entity;
import com.company.hightechcomapny.task.entity.Task;
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
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    private UUID id;
    private String name;
    private Integer salary;

    @ToString.Exclude
    private String password;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private String picture;

    @Column(name = "last_login")
    private LocalDateTime lastLoginDateTime;


    @CollectionTable(name = "employees__roles", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "employee", cascade = CascadeType.REMOVE)
    private List<Task> tasks;

}
