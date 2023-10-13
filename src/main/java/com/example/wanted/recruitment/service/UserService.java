package com.example.wanted.recruitment.service;

import com.example.wanted.exception.BusinessLogicException;
import com.example.wanted.exception.ExceptionCode;
import com.example.wanted.recruitment.entity.Recruitment;
import com.example.wanted.recruitment.entity.Users;
import com.example.wanted.recruitment.repository.RecruitmentRepository;
import com.example.wanted.recruitment.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService {
    private UserRepository userRepository;
    private RecruitmentRepository recruitmentRepository;

    public UserService(UserRepository userRepository,
                       RecruitmentRepository recruitmentRepository) {
        this.userRepository = userRepository;
        this.recruitmentRepository = recruitmentRepository;
    }

    public Users apply(Long userId, Long recruitmentId){
        // 존재하는 사용자인지 확인
//        boolean exist = findUser(userId);
//        if(!exist){
//            throw new BusinessLogicException(ExceptionCode.USER_NOT_FOUND);
//        }
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.RECRUITMENT_NOT_FOUND));
        Users users = new Users();
        users.setRecruitment(recruitment);
        users.setUserId(userId);
        return userRepository.save(users);
    }

//    public Boolean findUser(Long userId){
//        Optional<Users> user = userRepository.findById(userId);
//        return user.isPresent();
//    }
}
