package com.project.stacktrace.models.dto;

import lombok.Data;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class RequestCourseDTO {
    @NotEmpty(message = "The field 'name' is not empty.")
    private String name;
    @NotNull(message = "The field 'totalTimeHour' is not null.")
    private Integer totalTimeHour;
    @NotEmpty(message = "The field 'description' is not empty.")
    private String description;
    @NotNull(message = "The field 'qualificationPass' is not null.")
    private Integer qualificationPass;
}
