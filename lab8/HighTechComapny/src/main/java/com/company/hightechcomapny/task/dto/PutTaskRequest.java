package com.company.hightechcomapny.task.dto;

import com.company.hightechcomapny.task.domain.StatsHolder;
import com.company.hightechcomapny.task.entity.Priority;
import com.company.hightechcomapny.task.validation.binging.ValidTaskStats;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@ValidTaskStats
public class PutTaskRequest implements StatsHolder {
    @NotBlank
    private String description;
    private LocalDate deadline;
    private Priority priority;

    @Override
    public Integer getDescriptionLength() {
        return this.description.length();
    }
}
