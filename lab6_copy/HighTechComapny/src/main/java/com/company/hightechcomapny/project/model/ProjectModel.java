package com.company.hightechcomapny.project.model;
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
public class ProjectModel {
    private UUID id;
    private String name;
    private Integer budget;
    @Singular
    private List<Task> tasks;
}
