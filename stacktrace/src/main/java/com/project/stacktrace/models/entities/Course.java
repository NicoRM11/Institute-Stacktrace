package com.project.stacktrace.models.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "COURSES")
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NAME" , nullable = false)
    private String name;
    @Column(name = "TOTAL_HOUR" , nullable = false)
    private Integer totalTimeHour;
    @Column(name = "DESCRIPTION" , nullable = false)
    private String description;
    @Column(name = "QUALIFICATION_PASS")
    private Integer qualificationPass;
    @ManyToOne
    @JoinColumn(name = "ID_TEACHER")
    private Teacher teacher;
    @ElementCollection
    @JoinTable(
            name = "STUDENT_COURSE",
            joinColumns = {@JoinColumn(name = "ID_COURSE")}
    )
    private List<JoinedStudentCourse> joined = new ArrayList<JoinedStudentCourse>();
}
