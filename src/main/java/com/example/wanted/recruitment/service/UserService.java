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
        boolean exist = findUser(userId, recruitmentId);
        if(exist){
            throw new BusinessLogicException(ExceptionCode.ALREADY_APPLIED);
        }
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.RECRUITMENT_NOT_FOUND));
        Users users = new Users();
        users.setRecruitment(recruitment);
        users.setUserId(userId);
        return userRepository.save(users);
    }
    // 공고 지원여부 판단
    public Boolean findUser(Long userId, Long recruitmentId){
        Optional<Users> user = userRepository.findByUserIdAndRecruitmentId(userId, recruitmentId);
        return user.isPresent();
    }
}