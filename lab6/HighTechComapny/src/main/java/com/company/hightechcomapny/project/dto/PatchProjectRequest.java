package com.company.hightechcomapny.project.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PatchProjectRequest {
    private UUID id;
    private String name;
    private Integer budget;
}
