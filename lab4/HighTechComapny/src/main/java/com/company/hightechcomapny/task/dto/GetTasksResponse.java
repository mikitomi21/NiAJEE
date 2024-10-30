package com.company.hightechcomapny.task.dto;

import com.company.hightechcomapny.task.entity.Priority;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetTasksResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Task {
        private UUID id;
        private String description;
        private LocalDate deadline;
        private Priority priority;
    }

    @Singular
    private List<Task> tasks;
}
