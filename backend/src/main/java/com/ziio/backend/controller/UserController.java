package com.ziio.backend.controller;

import com.ziio.backend.dto.UserDTO;
import com.ziio.backend.service.UserService;
import com.ziio.backend.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;

    // Jwt 토큰을 받아 특정 유저 정보를 반환하는 메소드
    @GetMapping
    public ResponseEntity<UserDTO.Info> getUserDetails(@RequestHeader("Authorization") String authorizationHeader) {
        // 헤더에서 토큰 추출
        String jwtToken = jwtUtil.getJwtTokenFromHeader(authorizationHeader);
        // 유저 이메일
        final String userEmail = jwtUtil.getEmailFromToken(jwtToken);
        // 유저 이름
        final String userName = userService.getUserNameByEmail(userEmail);
        // 응답 객체 생성
        UserDTO.Info userinfo = UserDTO.Info.builder()
                                            .email(userEmail)
                                            .name(userName)
                                            .build();
        // ResponseEntity에 응답 객체를 담아서 반환
        return ResponseEntity.ok(userinfo);
    }

}