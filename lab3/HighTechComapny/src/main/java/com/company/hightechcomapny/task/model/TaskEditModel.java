package com.company.hightechcomapny.task.model;

import java.time.LocalDate;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class TaskEditModel {
    private String description;
}
