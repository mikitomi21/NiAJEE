package com.company.hightechcomapny.task.model;
import com.company.hightechcomapny.employee.entity.Employee;
import com.company.hightechcomapny.project.model.ProjectModel;
import com.company.hightechcomapny.task.domain.StatsHolder;
import com.company.hightechcomapny.task.entity.Priority;
import com.company.hightechcomapny.task.validation.binging.ValidTaskStats;
import com.company.hightechcomapny.task.validation.group.TaskModelGroup;
import jakarta.enterprise.context.ConversationScoped;
import jakarta.inject.Named;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@ValidTaskStats(groups = TaskModelGroup.class)
@Named
@ConversationScoped
public class TaskCreateModel implements StatsHolder, Serializable {
    private UUID id;
    @NotBlank
    private String description;
    private LocalDate deadline;
    private Priority priority;

    private Employee employee;
    private ProjectModel project;

    @Override
    public Integer getDescriptionLength(){
        return this.description.length();
    }
}
