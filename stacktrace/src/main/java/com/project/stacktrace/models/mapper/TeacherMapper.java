package com.project.stacktrace.models.mapper;

import com.project.stacktrace.models.dto.RequestTeacherDTO;
import com.project.stacktrace.models.entities.Teacher;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import com.project.stacktrace.models.dto.ResponseTeacherDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.ArrayList;
@Component
public class TeacherMapper {
    private ModelMapper modelMapper = new ModelMapper();
    public Teacher map(RequestTeacherDTO request){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return this.modelMapper.map(request , Teacher.class);
    }
    public ResponseTeacherDTO map(Teacher entity){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return this.modelMapper.map(entity, ResponseTeacherDTO.class);
    }

    public List<ResponseTeacherDTO> mapListDto(List<Teacher> teacher){
        List<ResponseTeacherDTO> dtos = new ArrayList<>();
        for(Teacher aux:teacher){
            dtos.add(this.map(aux));
        }
        return dtos;
    }
}
