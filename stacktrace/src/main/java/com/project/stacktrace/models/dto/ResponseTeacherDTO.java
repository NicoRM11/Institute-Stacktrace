package com.project.stacktrace.models.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResponseTeacherDTO {
    private String firstName;
    private String lastName;
    private String address;
    private Long phoneNumber;
    private List<String> capacitations;
    private List<String> titles;
}
