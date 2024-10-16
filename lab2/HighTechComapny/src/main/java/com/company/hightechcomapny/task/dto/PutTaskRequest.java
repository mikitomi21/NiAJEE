package com.company.hightechcomapny.task.dto;

import com.company.hightechcomapny.task.entity.Priority;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode

public class PutTaskRequest {
    private String description;
    private Date deadline;
    private Priority priority;
}
