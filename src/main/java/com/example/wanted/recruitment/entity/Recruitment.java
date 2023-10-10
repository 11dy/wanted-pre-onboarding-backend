package com.example.wanted.recruitment.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Recruitment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="recruitment_id")
    private Long id;

    private String position;
    private String reward;
    private String description;

    @ManyToOne
    @JoinColumn(name="company_id")
    private Company company;



}
