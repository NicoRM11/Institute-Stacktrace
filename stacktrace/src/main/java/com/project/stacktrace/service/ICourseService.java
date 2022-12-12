package com.project.stacktrace.service;



import com.project.stacktrace.models.dto.RequestCourseDTO;
import com.project.stacktrace.models.dto.ResponseCourseDTO;
import com.project.stacktrace.models.dto.ResponseStudentDTO;

import java.util.List;

public interface ICourseService {

    public ResponseCourseDTO createCourse(RequestCourseDTO request) throws Exception;
    public ResponseCourseDTO updateCourse(Long id , RequestCourseDTO request) throws Exception;
    public List<ResponseCourseDTO> getAllCourses() throws Exception;
    public String deleteCourse(Long id) throws Exception;
    public List<ResponseStudentDTO> courseStudentsList(Long id) throws Exception;
    public List<ResponseStudentDTO> courseStudentsPassList(Long id) throws Exception;

}
