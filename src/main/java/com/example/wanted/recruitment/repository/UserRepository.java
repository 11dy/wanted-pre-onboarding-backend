package com.example.wanted.recruitment.repository;

import com.example.wanted.recruitment.entity.Recruitment;
import com.example.wanted.recruitment.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUserIdAndRecruitmentId(Long userId, Long recruitment);
}