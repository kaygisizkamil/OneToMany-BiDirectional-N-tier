package com.example.OneToManyBiDirectional.business.concretes;

import com.example.OneToManyBiDirectional.business.abstracts.JobService;
import com.example.OneToManyBiDirectional.domain.Company;
import com.example.OneToManyBiDirectional.domain.Job;
import com.example.OneToManyBiDirectional.dto.JobDTO;
import com.example.OneToManyBiDirectional.repository.CompanyRepository;
import com.example.OneToManyBiDirectional.repository.JobRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class JobServiceImpl implements JobService {
    private JobRepository jobRepository;
    private CompanyRepository companyRepository;
    public JobServiceImpl(JobRepository jobRepository,CompanyRepository companyRepository) {
        this.jobRepository = jobRepository;
        this.companyRepository=companyRepository;
    }

    @Override
    public JobDTO createJob(JobDTO jobDTO) {
        Job job = new Job();
        job.setField(jobDTO.getField());
        Company company = companyRepository.findById(jobDTO.getCompanyId()).orElseThrow(() -> new EntityNotFoundException("Company not found"));
        job.setCompany(company);
        job = jobRepository.save(job);
        jobDTO.setId(job.getId());
        return jobDTO;
    }
    @Override
    public List<JobDTO> getAllJobs() {
        List<Job> jobs = jobRepository.findAll();
        return jobs.stream().map(job -> {
            JobDTO jobDTO = new JobDTO();
            jobDTO.setId(job.getId());
            jobDTO.setField(job.getField());
            jobDTO.setCompanyId(job.getCompany().getId());
            return jobDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public JobDTO updateJob(JobDTO jobDTO) {
        Job job = jobRepository.findById(jobDTO.getId()).orElseThrow(() -> new EntityNotFoundException("Job not found"));
        job.setField(jobDTO.getField());
        Company company = companyRepository.findById(jobDTO.getCompanyId()).orElseThrow(() -> new EntityNotFoundException("Company not found"));
        job.setCompany(company);
        job = jobRepository.save(job);
        return jobDTO;
    }
    @Override
    public JobDTO getJob(Long id) {
        Job job = jobRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Job not found"));
        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(job.getId());
        jobDTO.setField(job.getField());
        jobDTO.setCompanyId(job.getCompany().getId());
        return jobDTO;
    }

    @Override
    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }


}