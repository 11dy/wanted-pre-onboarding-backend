package com.example.wanted.recruitment.repository;

import com.example.wanted.recruitment.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {

}
