package com.example.wanted.recruitment.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(force = true)
@Getter
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="company_id")
    private Long id;

    @NotNull
    private String companyName;

    @NotNull
    private String country;

    @NotNull
    private String region;

    @NotNull
    private String skill;

    @OneToMany(mappedBy = "company")
    private List<Recruitment> recruitments = new ArrayList<>();

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }


}
