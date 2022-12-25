package com.example.OneToManyBiDirectional.dto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDTO {
    @JsonIgnore
    private Long id;
    private String name;
    // i can hear you yeah it could have been make more sense to use JobDto jobs but
    //i wanted it do it that way if you also implement that please let me know to add it in repo
    @JsonIgnore
    private Set<JobDTO> jobs;

    // Getters and setters for the properties
}