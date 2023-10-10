package com.example.wanted.recruitment.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="company_id")
    private Long id;

    private String companyName;

    private String country;

    private String region;

    private String skill;

    @OneToMany(mappedBy = "company") // 읽기만 가능
    private List<Recruitment> recruitments = new ArrayList<>();
}
