package com.project.stacktrace.service;

import com.project.stacktrace.models.dto.RequestTeacherDTO;
import com.project.stacktrace.models.dto.ResponseCourseDTO;
import com.project.stacktrace.models.dto.ResponseTeacherDTO;

import java.util.List;

public interface ITeacherService {

    public ResponseTeacherDTO createTeacher(RequestTeacherDTO request) throws Exception;
    public ResponseTeacherDTO updateTeacher(Long id , RequestTeacherDTO request) throws Exception;
    public List<ResponseTeacherDTO> getAllTeachers() throws Exception;
    public String deleteTeacher(Long id) throws Exception;
    public List<ResponseCourseDTO> teacherCourseList(Long id) throws Exception;
    public String addTeacherToCourse(Long id ,String courseName) throws Exception;
    public String removeTeacherFromCourse(Long id , String courseName) throws Exception;
}
