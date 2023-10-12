package com.example.wanted.recruitment.entity;

import com.sun.istack.NotNull;
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
    private String skill;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="company_id")
    private Company company;


    // 생성자 추가
    public Recruitment(String position, String reward, String description, Company company ) {
        this.position = position;
        this.reward = reward;
        this.description = description;
        this.company = company;
        this.skill = company.getSkill();
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCompany(Company company) {
        this.company = company;
        company.getRecruitments().add(this); // 서로가 바라보게 된다. > 연관관계 편의 메소드
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }
}
