//package com.example.wanted.recruitment.controller;
//
//import com.example.wanted.recruitment.dto.UserResponseDto;
//import com.example.wanted.recruitment.dto.util.SingleResponseDto;
//import com.example.wanted.recruitment.entity.Users;
//import com.example.wanted.recruitment.mapper.UserMapper;
//import com.example.wanted.recruitment.service.UserService;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/user")
//public class UserController {
//    private UserService userService;
//    private UserMapper mapper;
//
//    public UserController(UserService userService,
//                          UserMapper mapper) {
//        this.userService = userService;
//        this.mapper = mapper;
//    }
//
//    @PostMapping("/submission")
//    public ResponseEntity<?> submission(@RequestParam("user-id")Long userId,
//                                        @RequestParam("recruitment-id")Long recruitmentId){
//        Users usersSubmission = userService.apply(userId, recruitmentId);
//        UserResponseDto response = mapper.UserToUserResponseDto(usersSubmission);
//
//        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
//    }
//}
package com.example.wanted.recruitment.controller;

import com.example.wanted.exception.BusinessLogicException;
import com.example.wanted.exception.ErrorResponseDto;
import com.example.wanted.recruitment.dto.UserResponseDto;
import com.example.wanted.recruitment.dto.util.SingleResponseDto;
import com.example.wanted.recruitment.entity.Users;
import com.example.wanted.recruitment.mapper.UserMapper;
import com.example.wanted.recruitment.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    private UserMapper mapper;
    public UserController(UserService userService,
                          UserMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }
    @PostMapping("/submission")
    public ResponseEntity<?> submission(@RequestParam("user-id")Long userId,
                                        @RequestParam("recruitment-id")Long recruitmentId){
        try {
            Users usersSubmission = userService.apply(userId, recruitmentId);
            UserResponseDto response = mapper.UserToUserResponseDto(usersSubmission);
            return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
        }catch(BusinessLogicException ex){
            ErrorResponseDto errorResponse = new ErrorResponseDto("Business Logic Error", ex.getExceptionCode().getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

    }
}
