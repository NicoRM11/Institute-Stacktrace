package com.project.stacktrace.models.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.stacktrace.utils.SexEnum;
import com.project.stacktrace.utils.TypeDNIEnum;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "STUDENTS")
@Data
@SQLDelete(sql = "UPDATE STUDENTS SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "DNI_TYPE")
    @Enumerated(EnumType.STRING)
    private TypeDNIEnum typeDni;
    @Column(name = "DNI_NUMBER" , nullable = false)
    private Long dni;
    @Column(name = "FIRST_NAME" , nullable = false)
    private String firstName;
    @Column(name = "LAST_NAME" , nullable = false)
    private String lastName;
    @Column(name = "BORN_DATE" , nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",timezone="UTC-3")
    private Timestamp bornDate;
    @Column(name = "ADDRESS" , nullable = false)
    private String address;
    @Column(name = "SEX")
    @Enumerated(EnumType.STRING)
    private SexEnum sex;
    @Column(name = "PHONE_NUMBER" , nullable = false)
    private Long phoneNumber;
    @ElementCollection
    @JoinTable(
            name = "STUDENT_COURSE",
            joinColumns = {@JoinColumn(name = "ID_STUDENT")}
    )
    private List<JoinedStudentCourse> joined = new ArrayList<JoinedStudentCourse>();

    @Column(name = "DELETED")
    private Boolean deleted = Boolean.FALSE;
}
