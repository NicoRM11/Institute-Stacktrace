package com.project.stacktrace.models.dto;

import com.project.stacktrace.models.entities.Teacher;
import lombok.Data;
@Data
public class ResponseCourseDTO {

    private String name;
    private Integer totalTimeHour;
    private String description;
    private Integer qualificationPass;
    private Teacher teacher;

}
