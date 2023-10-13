package com.example.wanted.recruitment.mapper;

import com.example.wanted.recruitment.dto.UserResponseDto;
import com.example.wanted.recruitment.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    public UserResponseDto UserToUserResponseDto(Users usersSubmission){
        if(usersSubmission == null){
            return null;
        }
        UserResponseDto result = new UserResponseDto();
        result.setUserId(usersSubmission.getUserId());
        result.setRecruitmentId(usersSubmission.getRecruitment().getId());

        return result;
    }

}
