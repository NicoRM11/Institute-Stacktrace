package com.project.stacktrace.models.entities;

import lombok.Data;

import javax.persistence.*;

@Embeddable
@Data
public class JoinedStudentCourse {
    @Column(name = "QUALIFICATION")
    private Integer qualification;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_STUDENT", insertable = false , updatable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_COURSE", insertable = false , updatable = false)
    private Course course;
}
