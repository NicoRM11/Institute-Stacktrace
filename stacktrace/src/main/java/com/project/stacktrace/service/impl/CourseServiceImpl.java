package com.project.stacktrace.service.impl;

import com.project.stacktrace.exceptions.DataNotFoundException;
import com.project.stacktrace.exceptions.ResourceNotFoundException;
import com.project.stacktrace.exceptions.UserIsExistException;
import com.project.stacktrace.models.dto.RequestCourseDTO;
import com.project.stacktrace.models.dto.ResponseCourseDTO;
import com.project.stacktrace.models.dto.ResponseStudentDTO;
import com.project.stacktrace.models.entities.Course;
import com.project.stacktrace.models.entities.Student;
import com.project.stacktrace.models.mapper.CourseMapper;
import com.project.stacktrace.models.mapper.StudentMapper;
import com.project.stacktrace.repositories.ICourseRepository;
import com.project.stacktrace.repositories.IStudentRepository;
import com.project.stacktrace.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private ICourseRepository courseRepository;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private IStudentRepository studentRepository;
    @Override
    @Transactional
    public ResponseCourseDTO createCourse(RequestCourseDTO request) throws Exception {
        if (!courseRepository.findByName(request.getName()).isPresent()) {
            Course course = courseMapper.map(request);
            courseRepository.save(course);
            return courseMapper.map(course);
        } else{
            throw new UserIsExistException("The course is already exist");
        }
    }

    @Override
    @Transactional
    public ResponseCourseDTO updateCourse(Long id, RequestCourseDTO request) throws Exception {
        Optional<Course> findCourse = courseRepository.findById(id);
        if (findCourse.isPresent()){
            Course course = findCourse.get();
            Course updateCourse = updateCourseFields(course, request);
            courseRepository.save(updateCourse);
            return courseMapper.map(updateCourse);
        } else{
            throw new ResourceNotFoundException("Id course not found");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseCourseDTO> getAllCourses() throws Exception {
        List<Course> listCourses = courseRepository.findAll();
        if(listCourses.size() > 0){
            return courseMapper.mapListDto(listCourses);
        } else{
            throw new DataNotFoundException("Courses list is empty");
        }
    }

    @Override
    @Transactional
    public String deleteCourse(Long id) throws Exception {
        Optional<Course> find = courseRepository.findById(id);
        if (find.isPresent()){
            Course course = find.get();
            courseRepository.delete(course);
            return "The course was succesfully removed";
        } else {
            throw new ResourceNotFoundException("Id course not found");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseStudentDTO> courseStudentsList(Long id) throws Exception {
        Optional<Course> findCourse = courseRepository.findById(id);
        if (findCourse.isPresent()){
           List<Student> listStudents = studentRepository.courseStudentsList(id);
           return studentMapper.mapListDto(listStudents);
        } else {
            throw new ResourceNotFoundException("Id course not found");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseStudentDTO> courseStudentsPassList(Long id) throws Exception {
        Optional<Course> findCourse = courseRepository.findById(id);
        if (findCourse.isPresent()){
            List<Student> listStudents = studentRepository.studentsPassList(id);
            return studentMapper.mapListDto(listStudents);
        } else {
            throw new ResourceNotFoundException("Id course not found");
        }
    }

    private Course updateCourseFields(Course course , RequestCourseDTO request){
        course.setName(request.getName());
        course.setTotalTimeHour(request.getTotalTimeHour());
        course.setDescription(request.getDescription());
        course.setQualificationPass(request.getQualificationPass());
        return course;
    }

}
