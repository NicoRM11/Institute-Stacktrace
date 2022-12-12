package com.project.stacktrace.repositories;

import com.project.stacktrace.models.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ITeacherRepository extends JpaRepository<Teacher,Long> {

    public Optional<Teacher> findByDni(Long dni);
    @Query("SELECT t FROM Teacher t WHERE t.deleted = 0")
    public List<Teacher> getProfessorList();



}
