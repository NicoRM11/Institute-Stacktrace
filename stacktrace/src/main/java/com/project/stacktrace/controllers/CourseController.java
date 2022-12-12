package com.project.stacktrace.controllers;

import com.project.stacktrace.models.dto.RequestCourseDTO;
import com.project.stacktrace.models.dto.ResponseCourseDTO;
import com.project.stacktrace.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private ICourseService courseService;
    @PostMapping("/register")
    public ResponseEntity<?> create(@RequestBody @Valid RequestCourseDTO request) throws Exception{
        return ResponseEntity.ok().body(courseService.createCourse(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id , @RequestBody @Valid RequestCourseDTO request) throws Exception{
        return ResponseEntity.ok().body(courseService.updateCourse(id, request));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ResponseCourseDTO>> read() throws Exception{
        return ResponseEntity.ok().body(courseService.getAllCourses());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok().body(courseService.deleteCourse(id));
    }

    @GetMapping("/findStudents/{id}")
    public ResponseEntity<?> studentsListByCourseId(@PathVariable Long id) throws Exception{
        return ResponseEntity.ok().body(courseService.courseStudentsList(id));
    }

    @GetMapping("/findPassStudents/{id}")
    public ResponseEntity<?> studentsPassListByCourseId(@PathVariable Long id) throws Exception{
        return ResponseEntity.ok().body(courseService.courseStudentsPassList(id));
    }

}
