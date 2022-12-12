package com.project.stacktrace.models.mapper;


import com.project.stacktrace.models.dto.RequestStudentDTO;
import com.project.stacktrace.models.dto.ResponseStudentDTO;
import com.project.stacktrace.models.entities.Student;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentMapper {
    private ModelMapper modelMapper = new ModelMapper();
    public Student map(RequestStudentDTO request){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return this.modelMapper.map(request , Student.class);
    }
    public ResponseStudentDTO map(Student entity){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return this.modelMapper.map(entity, ResponseStudentDTO.class);
    }

    public List<ResponseStudentDTO> mapListDto(List<Student> students){
        List<ResponseStudentDTO> dtos = new ArrayList<>();
        for(Student aux:students){
            dtos.add(this.map(aux));
        }
        return dtos;
    }
}
