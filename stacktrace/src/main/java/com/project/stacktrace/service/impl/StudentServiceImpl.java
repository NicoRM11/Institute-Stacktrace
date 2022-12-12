package com.project.stacktrace.service.impl;

import com.project.stacktrace.exceptions.DataNotFoundException;
import com.project.stacktrace.exceptions.ResourceNotFoundException;
import com.project.stacktrace.exceptions.UserIsExistException;
import com.project.stacktrace.models.dto.RequestStudentDTO;
import com.project.stacktrace.models.dto.ResponseCourseDTO;
import com.project.stacktrace.models.dto.ResponseStudentDTO;
import com.project.stacktrace.models.entities.Course;
import com.project.stacktrace.models.entities.Student;
import com.project.stacktrace.models.mapper.CourseMapper;
import com.project.stacktrace.models.mapper.StudentMapper;
import com.project.stacktrace.repositories.ICourseRepository;
import com.project.stacktrace.repositories.IStudentRepository;
import com.project.stacktrace.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private ICourseRepository courseRepository;
    @Autowired
    private IStudentRepository studentRepository;
    @Override
    @Transactional
    public ResponseStudentDTO createStudent(RequestStudentDTO request) throws Exception{
        if (!studentRepository.findByDni(request.getDni()).isPresent()) {
            Student student = studentMapper.map(request);
            studentRepository.save(student);
            return studentMapper.map(student);
        } else{
            throw new UserIsExistException("The DNI is already exist");
        }
    }

    @Override
    @Transactional
    public ResponseStudentDTO updateStudent(Long id, RequestStudentDTO request) throws Exception {
        Optional<Student> findStudent = studentRepository.findById(id);
        if (findStudent.isPresent()){
            Student student = findStudent.get();
            if (!student.getDeleted()){
                Student updateStudent = updateStudentFields(student , request);
                studentRepository.save(updateStudent);
                return studentMapper.map(updateStudent);
            } else {
                throw new UserIsExistException("Change is not possible, because the student was deleted");
            }
        } else{
            throw new ResourceNotFoundException("Id student not found");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseStudentDTO> getAllStudents() throws Exception {
        List<Student> listStudents = studentRepository.getStudentList();
        if(listStudents.size() > 0){
            return studentMapper.mapListDto(listStudents);
        } else{
            throw new DataNotFoundException("Students list is empty");
        }
    }

    @Override
    @Transactional
    public String deleteStudent(Long id) throws Exception {
        Optional<Student> find = studentRepository.findById(id);
        if (find.isPresent()){
            Student student = find.get();
            if (!student.getDeleted()){
                studentRepository.delete(student);
                return "The student was succesfully removed";
            } else {
                throw new UserIsExistException("The action is not possible, because the student was deleted");
            }
        } else {
            throw new ResourceNotFoundException("Id student not found");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseCourseDTO> studentCourseList(Long id) throws Exception {
        Optional<Student> findStudent = studentRepository.findById(id);
        if (findStudent.isPresent()){
          List<Course> listCourses = courseRepository.studentCourseList(id);
            return courseMapper.mapListDto(listCourses);
        } else{
            throw new ResourceNotFoundException("Id student not found");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseCourseDTO> courseListStudentPass(Long id) throws Exception {
        Optional<Student> findStudent = studentRepository.findById(id);
        if (findStudent.isPresent()){
            List<Course> listCourses = courseRepository.courseListStudentPass(id);
            return courseMapper.mapListDto(listCourses);
        } else{
            throw new ResourceNotFoundException("Id student not found");
        }
    }

    @Override
    @Transactional
    public String addStudentToCourse(Long idStudent , String courseName) throws Exception {
        Optional<Student> findStudent = studentRepository.findById(idStudent);
        if (findStudent.isPresent()){
           Student student = findStudent.get();
           Optional<Course> findCourse = courseRepository.findByName(courseName);
           if (findCourse.isPresent()){
               if (!studentRepository.getStudentByCourseName(courseName).isPresent()){
                   //findCourse.get().getJoined();
                   return "Student added";
               } else {
                   throw new UserIsExistException("Student was added to course");
               }
           } else{
               throw new ResourceNotFoundException("The course name not found");
           }
        } else {
            throw new ResourceNotFoundException("Id student not found");
        }
    }

    @Override
    @Transactional
    public String removeStudentFromCourse(Long idStudent , String courseName) throws Exception {
        Optional<Student> findStudent = studentRepository.findById(idStudent);
        if (findStudent.isPresent()){
            Student student = findStudent.get();
            Optional<Course> findCourse = courseRepository.findByName(courseName);
            if (findCourse.isPresent()) {
                if (!studentRepository.getStudentByCourseName(courseName).isPresent()) {
                    //findCourse.get().getStudents().remove(student);
                    return "Student removed";
                } else{
                    throw new UserIsExistException("Student was removed from course");
                }
            } else{
                throw new ResourceNotFoundException("The course name not found");
            }
            } else{
            throw new ResourceNotFoundException("Id student not found");
        }
    }

    private Student updateStudentFields(Student student , RequestStudentDTO request){
        student.setTypeDni(request.getTypeDni());
        student.setDni(request.getDni());
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setBornDate(request.getBornDate());
        student.setAddress(request.getAddress());
        student.setSex(request.getSex());
        student.setPhoneNumber(request.getPhoneNumber());
        return student;
    }
}
