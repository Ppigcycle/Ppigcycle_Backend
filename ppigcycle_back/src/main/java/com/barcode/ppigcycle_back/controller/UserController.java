package com.barcode.ppigcycle_back.controller;

import com.barcode.ppigcycle_back.domain.User;
import com.barcode.ppigcycle_back.domain.dto.UserDto;
import com.barcode.ppigcycle_back.domain.request.LoginRequest;
import com.barcode.ppigcycle_back.exception.UserPasswordMismatchException;
import com.barcode.ppigcycle_back.repository.UserRepository;
import com.barcode.ppigcycle_back.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping("/users/new-user") // 회원가입
    public ResponseEntity<String> join(@RequestBody UserDto userDto) throws UserPasswordMismatchException {
        if (!userDto.getPassword().equals(userDto.getCheckpassword())) {
            throw new UserPasswordMismatchException("패스워드가 일치하지 않습니다.");
        }
        userService.save(userDto);
        return ResponseEntity.ok("join success");
    }

    @PostMapping("/login") // 로그인
    public ResponseEntity<Boolean> login(@RequestBody LoginRequest request) {
        boolean isAuthenticated = userService.checkLogin(request.getId(), request.getPassword());
        return isAuthenticated ? ResponseEntity.ok(true) : ResponseEntity.badRequest().body(false);
    }

    @GetMapping("/checkDuplicateId/{id}") // 아이디 중복 확인
    public boolean checkDuplicateId(@PathVariable String id) {
        return userService.checkDuplicateId(id);
    } // 중복이면 true, 중복 아니면 false

    @GetMapping("/checkDuplicateNickname/{nickname}")
    public boolean checkDuplicateNickname(@PathVariable String nickname) {
        return userService.checkDuplicateNickname(nickname);
    }

    @GetMapping("/user/{userId}/day")
    public ResponseEntity<String> getUserCustomDay(@PathVariable String userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            String date = user.getDate();
            if (date != null && !date.isEmpty()) {
                return ResponseEntity.ok(date);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Custom day not found for user " + userId);
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found with id " + userId);
        }

    }
}