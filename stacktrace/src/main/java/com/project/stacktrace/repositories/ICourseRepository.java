package com.project.stacktrace.repositories;

import com.project.stacktrace.models.entities.Course;
import com.project.stacktrace.models.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICourseRepository extends JpaRepository<Course , Long> {

    public Optional<Course> findByName(String name);

    @Query("SELECT c.teacher FROM Course c WHERE c.name = :name")
    public Optional<Teacher> findTeacherByCourseName(@Param("name") String name);

    @Query("SELECT c FROM Course c WHERE c.teacher.id = :id")
    public List<Course> getTeacherCoursesList(@Param("id") Long id);

    @Query("SELECT c FROM Course c INNER JOIN c.joined j WHERE j.student.id = :id")
    public List<Course> studentCourseList(@Param("id") Long id);

    @Query("SELECT c FROM Course c INNER JOIN c.joined j WHERE j.student.id = :id AND j.qualification >= c.qualificationPass AND j.student.deleted = 0")
    public List<Course> courseListStudentPass(@Param("id") Long id);
}
