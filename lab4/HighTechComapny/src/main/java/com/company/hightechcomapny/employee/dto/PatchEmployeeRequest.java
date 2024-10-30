package com.company.hightechcomapny.employee.dto;

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
public class PatchEmployeeRequest {
    private String name;
    private Integer salary;
    private String picture;
}
