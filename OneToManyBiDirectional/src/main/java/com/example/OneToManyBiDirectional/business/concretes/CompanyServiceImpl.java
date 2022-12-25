package com.example.OneToManyBiDirectional.business.concretes;

import com.example.OneToManyBiDirectional.business.abstracts.CompanyService;
import com.example.OneToManyBiDirectional.domain.Company;
import com.example.OneToManyBiDirectional.domain.Job;
import com.example.OneToManyBiDirectional.dto.CompanyDTO;
import com.example.OneToManyBiDirectional.dto.JobDTO;
import com.example.OneToManyBiDirectional.repository.CompanyRepository;
import com.example.OneToManyBiDirectional.repository.JobRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {
    private CompanyRepository companyRepository;
    private JobRepository jobRepository;
    public CompanyServiceImpl(CompanyRepository companyRepository,JobRepository jobRepository) {
        this.jobRepository=jobRepository;
        this.companyRepository = companyRepository;
    }
    @Override
    public CompanyDTO createCompany(CompanyDTO companyDTO) {
        Company company = new Company();
        company.setName(companyDTO.getName());
        company.setJobs(new HashSet<>());
        company = companyRepository.save(company);
        Set<Job> jobs = jobRepository.findByCompanyId(company.getId());
        CompanyDTO createdCompany = new CompanyDTO();
        createdCompany.setId(company.getId());
        createdCompany.setName(company.getName());
        createdCompany.setJobs(jobs.stream().map(job -> {
            JobDTO jobDTO = new JobDTO();
            jobDTO.setId(job.getId());
            jobDTO.setField(job.getField());
            jobDTO.setCompanyId(job.getCompany().getId());
            return jobDTO;
        }).collect(Collectors.toSet()));
        return createdCompany;
    }
    @Override
    public CompanyDTO getCompany(Long id) {
        Company company = companyRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Company not found"));
        Set<Job> jobs = jobRepository.findByCompanyId(company.getId());
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setId(company.getId());
        companyDTO.setName(company.getName());
        companyDTO.setJobs(jobs.stream().map(job -> {
            JobDTO jobDTO = new JobDTO();
            jobDTO.setId(job.getId());
            jobDTO.setField(job.getField());
            jobDTO.setCompanyId(job.getCompany().getId());
            return jobDTO;
        }).collect(Collectors.toSet()));
        return companyDTO;
    }

    @Override
    public CompanyDTO updateCompany(CompanyDTO companyDTO) {
        Company company = companyRepository.findById(companyDTO.getId()).orElseThrow(() -> new EntityNotFoundException("Company not found"));
        company.setName(companyDTO.getName());
        company = companyRepository.save(company);
        Set<Job> jobs = jobRepository.findByCompanyId(company.getId());
        CompanyDTO updatedCompany = new CompanyDTO();
        updatedCompany.setId(company.getId());
        updatedCompany.setName(company.getName());
        updatedCompany.setJobs(jobs.stream().map(job -> {
            JobDTO jobDTO = new JobDTO();
            jobDTO.setId(job.getId());
            jobDTO.setField(job.getField());
            jobDTO.setCompanyId(job.getCompany().getId());
            return jobDTO;
        }).collect(Collectors.toSet()));
        return updatedCompany;
    }

    @Override
    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }


    @Override
    public List<CompanyDTO> getAll() {
        List<Company> companies = companyRepository.findAll();
        List<CompanyDTO> companyDTOs = new ArrayList<>();
        for (Company company : companies) {
            Set<Job> jobs = jobRepository.findByCompanyId(company.getId());
            CompanyDTO companyDTO = new CompanyDTO();
            companyDTO.setId(company.getId());
            companyDTO.setName(company.getName());
            companyDTO.setJobs(jobs.stream().map(job -> {
                JobDTO jobDTO = new JobDTO();
                jobDTO.setId(job.getId());
                jobDTO.setField(job.getField());
                jobDTO.setCompanyId(job.getCompany().getId());
                return jobDTO;
            }).collect(Collectors.toSet()));
            companyDTOs.add(companyDTO);
        }
        return companyDTOs;
    }
}
