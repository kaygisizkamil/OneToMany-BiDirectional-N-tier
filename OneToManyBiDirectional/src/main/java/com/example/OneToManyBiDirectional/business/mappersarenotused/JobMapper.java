package com.example.OneToManyBiDirectional.business.mappersarenotused;

import com.example.OneToManyBiDirectional.domain.Job;
import com.example.OneToManyBiDirectional.dto.JobDTO;


//@Mapper


public interface JobMapper {//we could have used mapper i will mention that on many to many relationship
  //  JobMapper INSTANCE = Mappers.getMapper(JobMapper.class);

    JobDTO toDTO(Job job);
    Job fromDTO(JobDTO dto);
}