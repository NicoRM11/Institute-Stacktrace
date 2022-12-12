package com.project.stacktrace.models.mapper;


import com.project.stacktrace.models.dto.RequestCourseDTO;
import com.project.stacktrace.models.dto.ResponseCourseDTO;
import com.project.stacktrace.models.entities.Course;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CourseMapper {
    private ModelMapper modelMapper = new ModelMapper();
    public Course map(RequestCourseDTO request){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return this.modelMapper.map(request , Course.class);
    }
    public ResponseCourseDTO map(Course entity){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return this.modelMapper.map(entity,ResponseCourseDTO.class);
    }

    public List<ResponseCourseDTO> mapListDto(List<Course> courses){
        List<ResponseCourseDTO> dtos = new ArrayList<>();
        for(Course aux:courses){
            dtos.add(this.map(aux));
        }
        return dtos;
    }
}
