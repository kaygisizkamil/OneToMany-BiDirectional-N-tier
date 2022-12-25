package com.example.OneToManyBiDirectional.business.abstracts;

import com.example.OneToManyBiDirectional.dto.JobDTO;

import java.util.List;

public interface JobService {
    JobDTO createJob(JobDTO jobDTO);
    JobDTO getJob(Long id);
    JobDTO updateJob(JobDTO jobDTO);
    void deleteJob(Long id);
    public List<JobDTO> getAllJobs();
}