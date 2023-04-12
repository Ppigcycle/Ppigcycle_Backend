package com.barcode.ppigcycle_back.service;

import com.barcode.ppigcycle_back.domain.User;
import com.barcode.ppigcycle_back.domain.dto.UserDto;
import com.barcode.ppigcycle_back.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public void save(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(userDto.toEntity());
    }

//    @Override
//    public String login(String id, String password){ // 로그인
//        Optional<User> user = userRepository.findById(id);
//        if(user.get().getPassword().equals(user.get().getPassword())){
//            return "Success";
//        }
//        return "Failed";
//    }if(user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())){
//            return "true";
//        }
//        return "false";


    public String login(String id, String password){ // 로그인
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())){
            return "true";
        }
        else{
            return "false";
        }
    }

    public boolean checkLogin(String id, String password) {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            return false;
        }
        return passwordEncoder.matches(password, user.get().getPassword());
    }


    public boolean checkDuplicateNickname(String username) {
        return userRepository.findByNickname(username).isPresent();
    }


    public boolean checkDuplicateId(String id){
        return userRepository.findById(id).isPresent();
    }


}