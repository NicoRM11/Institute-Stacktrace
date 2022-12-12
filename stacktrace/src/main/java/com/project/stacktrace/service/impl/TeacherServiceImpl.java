package com.project.stacktrace.service.impl;

import com.project.stacktrace.exceptions.DataNotFoundException;
import com.project.stacktrace.exceptions.ResourceNotFoundException;
import com.project.stacktrace.exceptions.UserIsExistException;
import com.project.stacktrace.models.dto.RequestTeacherDTO;
import com.project.stacktrace.models.dto.ResponseCourseDTO;
import com.project.stacktrace.models.dto.ResponseTeacherDTO;
import com.project.stacktrace.models.entities.Course;
import com.project.stacktrace.models.entities.Teacher;
import com.project.stacktrace.models.mapper.CourseMapper;
import com.project.stacktrace.models.mapper.TeacherMapper;
import com.project.stacktrace.repositories.ICourseRepository;
import com.project.stacktrace.repositories.ITeacherRepository;
import com.project.stacktrace.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements ITeacherService {
    @Autowired
    private ITeacherRepository teacherRepository;
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private ICourseRepository courseRepository;
    @Autowired
    private CourseMapper courseMapper;
    @Override
    @Transactional
    public ResponseTeacherDTO createTeacher(RequestTeacherDTO request) throws Exception {
        if (!teacherRepository.findByDni(request.getDni()).isPresent()){
         Teacher teacher = teacherMapper.map(request);
         teacherRepository.save(teacher);
         return teacherMapper.map(teacher);
        }
        else {
           throw new UserIsExistException("The DNI is already exist");
        }
    }

    @Override
    @Transactional
    public ResponseTeacherDTO updateTeacher(Long id, RequestTeacherDTO request) throws Exception{
        Optional<Teacher> find =teacherRepository.findById(id);
        if (find.isPresent()){
            Teacher teacher = find.get();
            if (!teacher.getDeleted()){
                Teacher updateTeacher = updateTeacherFields(teacher, request);
                teacherRepository.save(updateTeacher);
                return teacherMapper.map(updateTeacher);
            } else {
             throw new UserIsExistException("Change is not possible, because the professor was deleted");
            }
        } else{
            throw new ResourceNotFoundException("Id professor not found");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseTeacherDTO> getAllTeachers() throws Exception{
        List<Teacher> listTeachers = teacherRepository.getProfessorList();
        if(listTeachers.size() > 0){
            return teacherMapper.mapListDto(listTeachers);
        } else{
            throw new DataNotFoundException("Professors list is empty");
        }
    }

    @Override
    @Transactional
    public String deleteTeacher(Long id) throws Exception {
        Optional<Teacher> find = teacherRepository.findById(id);
        if (find.isPresent()){
             Teacher teacher = find.get();
             if (!teacher.getDeleted()){
                 teacherRepository.delete(teacher);
                 return "The professor was succesfully removed";
             } else {
                 throw new UserIsExistException("The action is not possible, because the professor was deleted");
             }
        } else {
            throw new ResourceNotFoundException("Id professor not found");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseCourseDTO> teacherCourseList(Long id) throws Exception {
        Optional<Teacher> findTeacher = teacherRepository.findById(id);
        if (findTeacher.isPresent()){
            List<Course> teacherCourseList = courseRepository.getTeacherCoursesList(id);
            if (!teacherCourseList.isEmpty()){
                return courseMapper.mapListDto(teacherCourseList);
            } else{
                throw new DataNotFoundException("Teacher course list is empty");
            }
        } else{
            throw new ResourceNotFoundException("Id professor not found");
        }
    }

    @Override
    @Transactional
    public String addTeacherToCourse(Long idTeacher, String courseName) throws Exception {
        Optional<Teacher> findTeacher = teacherRepository.findById(idTeacher);
        if (findTeacher.isPresent()){
            Teacher teacher = findTeacher.get();
            Optional<Course> findCourse = courseRepository.findByName(courseName);
            if (findCourse.isPresent()){
                if (!courseRepository.findTeacherByCourseName(courseName).isPresent()){
                    findCourse.get().setTeacher(teacher);
                    return "Teacher added";
                } else{
                    throw new UserIsExistException("Teacher was added to course, or course is busy");
                }
            } else{
                throw new ResourceNotFoundException("The course name not found");
            }
        } else{
            throw new ResourceNotFoundException("Id teacher not found");
        }
    }

    @Override
    @Transactional
    public String removeTeacherFromCourse(Long idTeacher, String courseName) throws Exception {
        Optional<Teacher> findTeacher = teacherRepository.findById(idTeacher);
        if (findTeacher.isPresent()){
            Optional<Course> findCourse = courseRepository.findByName(courseName);
            if (findCourse.isPresent()){
                if (courseRepository.findTeacherByCourseName(courseName).isPresent()){
                    findCourse.get().setTeacher(null);
                    return "Teacher removed";
                } else{
                    throw new UserIsExistException("Teacher was removed from course");
                }
            } else{
                throw new ResourceNotFoundException("The course name not found");
            }
        } else{
            throw new ResourceNotFoundException("Id teacher not found");
        }
    }

    private Teacher updateTeacherFields(Teacher teacher, RequestTeacherDTO request){
        teacher.setTypeDni(request.getTypeDni());
        teacher.setDni(request.getDni());
        teacher.setFirstName(request.getFirstName());
        teacher.setLastName(request.getLastName());
        teacher.setBornDate(request.getBornDate());
        teacher.setAddress(request.getAddress());
        teacher.setSex(request.getSex());
        teacher.setPhoneNumber(request.getPhoneNumber());
        teacher.setCapacitations(request.getCapacitations());
        teacher.setTitles(request.getTitles());
        return teacher;
    }
}
