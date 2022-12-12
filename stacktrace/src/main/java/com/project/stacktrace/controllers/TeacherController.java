package com.project.stacktrace.controllers;

import com.project.stacktrace.models.dto.RequestTeacherDTO;
import com.project.stacktrace.models.dto.ResponseTeacherDTO;
import com.project.stacktrace.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private ITeacherService teacherService;
    @PostMapping("/register")
    public ResponseEntity<?> create(@RequestBody @Valid RequestTeacherDTO request) throws Exception{
        return ResponseEntity.ok().body(teacherService.createTeacher(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id , @RequestBody @Valid RequestTeacherDTO request) throws Exception{
        return ResponseEntity.ok().body(teacherService.updateTeacher(id, request));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ResponseTeacherDTO>> read() throws Exception{
        return ResponseEntity.ok().body(teacherService.getAllTeachers());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok().body(teacherService.deleteTeacher(id));
    }

    @GetMapping("/findCourses/{id}")
    public ResponseEntity<?> courseListByTeacherId(@PathVariable Long id) throws Exception{
        return ResponseEntity.ok().body(teacherService.teacherCourseList(id));
    }

    @PutMapping("/add/{courseName}/{id}")
    public ResponseEntity<?> addTeacherToCourse(@PathVariable String courseName , @PathVariable Long id) throws Exception {
        return ResponseEntity.ok().body(teacherService.addTeacherToCourse(id, courseName));
    }

    @PutMapping("/remove/{courseName}/{id}")
    public ResponseEntity<?> removeTeacherFromCourse(@PathVariable String courseName , @PathVariable Long id) throws Exception {
        return ResponseEntity.ok().body(teacherService.removeTeacherFromCourse(id, courseName));
    }
}
