package com.example.OneToManyBiDirectional.repository;

import com.example.OneToManyBiDirectional.domain.Company;
import com.example.OneToManyBiDirectional.domain.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}