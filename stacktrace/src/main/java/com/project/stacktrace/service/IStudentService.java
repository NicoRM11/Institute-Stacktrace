package com.project.stacktrace.service;



import com.project.stacktrace.models.dto.RequestStudentDTO;
import com.project.stacktrace.models.dto.ResponseCourseDTO;
import com.project.stacktrace.models.dto.ResponseStudentDTO;

import java.util.List;

public interface IStudentService {

    public ResponseStudentDTO createStudent(RequestStudentDTO request) throws Exception;
    public ResponseStudentDTO updateStudent(Long id , RequestStudentDTO request) throws Exception;
    public List<ResponseStudentDTO> getAllStudents() throws Exception;
    public String deleteStudent(Long id) throws Exception;
    public List<ResponseCourseDTO> studentCourseList(Long id) throws Exception;
    public String addStudentToCourse(Long id ,String courseName) throws Exception;
    public String removeStudentFromCourse(Long id , String courseName) throws Exception;
    public List<ResponseCourseDTO> courseListStudentPass(Long id) throws Exception;
}
