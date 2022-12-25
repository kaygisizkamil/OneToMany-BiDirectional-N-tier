package com.example.OneToManyBiDirectional.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(cascade=CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "company")
    @JsonIgnore
    private Set<Job> jobs=new HashSet<>();
}
/*
     @OneToMany
    Set<Job> jobs=new HashSet<>();
    //with unidirectional relationship at above there will be 3 table none of 2 has fk with table 2 it will hold two side'sis
      @OneToMany
    @JoinTable(
            name="CompanyJobs",
            joinColumns = @JoinColumn( name="company_id"),
            inverseJoinColumns = @JoinColumn( name="job_id")
    )
    Set<Job> jobs=new HashSet<>();//same above join table is manual by Sir Hibernate

    @OneToMany
    @JoinColumn(name="company_id")
    Set<Job> jobs=new HashSet<>();
    //there will be company_id inside jobs table nothins else



      @OneToMany
    Set<Job> jobs=new HashSet<>();
       @ManyToOne
    private Company company;
    job table have company_id column in it but by manual there will be a new table also

     @OneToMany
     @JoinColumn(commpany_id)
    Set<Job> jobs=new HashSet<>();
       @ManyToOne
    private Company company;
    //two table job has column that holds company id


 */
