package com.example.wanted.recruitment.repository;

import com.example.wanted.recruitment.entity.Recruitment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {
    Page<Recruitment> findAll( Pageable pageable);

    @Query("SELECT r FROM Recruitment r " +
            "WHERE r.company.companyName LIKE %:keyword% " +
            "OR r.company.country LIKE %:keyword% " +
            "OR r.company.region LIKE %:keyword% " +
            "OR r.position LIKE %:keyword% " +
            "OR r.reward LIKE %:keyword% " +
            "OR r.skill LIKE %:keyword%")
    Page<Recruitment> searchByKeyword(String keyword, Pageable pageable);


}
