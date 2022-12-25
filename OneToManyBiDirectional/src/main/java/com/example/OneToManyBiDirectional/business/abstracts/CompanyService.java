package com.example.OneToManyBiDirectional.business.abstracts;

import com.example.OneToManyBiDirectional.dto.CompanyDTO;

import java.util.List;

public interface CompanyService {
    CompanyDTO createCompany(CompanyDTO companyDTO);
    CompanyDTO getCompany(Long id);
    CompanyDTO updateCompany(CompanyDTO companyDTO);
    void deleteCompany(Long id);
    List<CompanyDTO> getAll();
}