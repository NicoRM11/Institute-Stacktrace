package com.project.stacktrace.models.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.stacktrace.utils.SexEnum;
import com.project.stacktrace.utils.TypeDNIEnum;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "TEACHERS")
@Data
@SQLDelete(sql = "UPDATE TEACHERS SET deleted = true WHERE id=?")
public class Teacher {

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
    @Column(name = "CAPACITATIONS")
    @ElementCollection
    @CollectionTable(name="CAPACITATIONS", joinColumns= @JoinColumn(name="ID_TEACHER"))
    private List<String> capacitations;

    @Column(name = "TITLES")
    @ElementCollection
    @CollectionTable(name="TITLES", joinColumns= @JoinColumn(name="ID_TEACHER"))
    private List<String> titles;
    @Column(name = "DELETED")
    private Boolean deleted = Boolean.FALSE;

}
