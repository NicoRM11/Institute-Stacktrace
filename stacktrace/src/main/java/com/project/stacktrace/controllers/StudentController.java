package com.project.stacktrace.controllers;

import com.project.stacktrace.models.dto.RequestStudentDTO;
import com.project.stacktrace.models.dto.ResponseStudentDTO;
import com.project.stacktrace.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private IStudentService studentService;
    @PostMapping("/register")
    public ResponseEntity<?> create(@RequestBody @Valid RequestStudentDTO request) throws Exception{
        return ResponseEntity.ok().body(studentService.createStudent(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id , @RequestBody @Valid RequestStudentDTO request) throws Exception{
        return ResponseEntity.ok().body(studentService.updateStudent(id, request));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ResponseStudentDTO>> read() throws Exception{
        return ResponseEntity.ok().body(studentService.getAllStudents());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok().body(studentService.deleteStudent(id));
    }

    @GetMapping("/findCourses/{id}")
    public ResponseEntity<?> courseListByStudentId(@PathVariable Long id) throws Exception{
        return ResponseEntity.ok().body(studentService.studentCourseList(id));
    }

    @GetMapping("/findPassCourses/{id}")
    public ResponseEntity<?> courseListPassByStudentId(@PathVariable Long id) throws Exception{
        return ResponseEntity.ok().body(studentService.courseListStudentPass(id));
    }

    @PutMapping("/add/{courseName}/{id}")
    public ResponseEntity<?> addStudentToCourse(@PathVariable String courseName , @PathVariable Long id) throws Exception {
        return ResponseEntity.ok().body(studentService.addStudentToCourse(id, courseName));
    }

    @PutMapping("/remove/{courseName}/{id}")
    public ResponseEntity<?> removeStudentFromCourse(@PathVariable String courseName , @PathVariable Long id) throws Exception {
        return ResponseEntity.ok().body(studentService.removeStudentFromCourse(id, courseName));
    }

}
