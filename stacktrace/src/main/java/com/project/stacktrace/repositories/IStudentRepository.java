package com.project.stacktrace.repositories;

import com.project.stacktrace.models.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IStudentRepository extends JpaRepository<Student , Long> {

    public Optional<Student> findByDni(Long dni);

    @Query("SELECT s FROM Student s WHERE s.deleted = 0")
    public List<Student> getStudentList();

    @Query("SELECT s FROM Student s INNER JOIN s.joined j WHERE j.course.name = :name")
    public Optional<Student> getStudentByCourseName(@Param("name") String name);

    @Query("SELECT s FROM Student s INNER JOIN s.joined j WHERE j.course.id = :id")
    public List<Student> courseStudentsList(@Param("id") Long id);

    @Query("SELECT s FROM Student s INNER JOIN s.joined j WHERE j.course.id = :id AND j.qualification >= j.course.qualificationPass AND s.deleted = 0")
    public List<Student> studentsPassList(@Param("id") Long id);



}
