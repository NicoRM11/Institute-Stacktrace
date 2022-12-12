package com.project.stacktrace.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.stacktrace.utils.SexEnum;
import com.project.stacktrace.utils.TypeDNIEnum;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
@Data
public class RequestStudentDTO {
    private TypeDNIEnum typeDni;
    @NotNull(message = "The field 'dni' is not null.")
    private Long dni;
    @NotEmpty(message = "The field 'firstName' is not empty.")
    private String firstName;
    @NotEmpty(message = "The field 'lastName' is not empty.")
    private String lastName;
    @NotNull(message = "The field 'bornDate' is not null.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",timezone="UTC-3")
    private Timestamp bornDate;
    @NotEmpty(message = "The field 'address' is not empty.")
    private String address;
    private SexEnum sex;
    @NotNull(message = "The field 'phoneNumber' is not null.")
    private Long phoneNumber;
}
