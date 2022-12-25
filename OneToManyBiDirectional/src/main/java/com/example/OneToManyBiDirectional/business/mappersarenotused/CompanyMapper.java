package com.example.OneToManyBiDirectional.business.mappersarenotused;

import com.example.OneToManyBiDirectional.domain.Company;
import com.example.OneToManyBiDirectional.dto.CompanyDTO;


//@Mapper


public interface CompanyMapper {
  //  CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    CompanyDTO toDTO(Company company);
    Company fromDTO(CompanyDTO dto);
}
