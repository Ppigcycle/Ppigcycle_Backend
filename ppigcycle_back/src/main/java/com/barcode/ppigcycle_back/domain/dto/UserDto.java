package com.barcode.ppigcycle_back.domain.dto;

import com.barcode.ppigcycle_back.domain.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private String id; // 유저 아이디
    private String password; // 유저 비밀번호
    private String checkpassword; // 비밀번호 확인
    private String nickname; // 유저 닉네임
    private String date; // 쓰레기 버리는 날

    public User toEntity(){
        return User.builder()
                .id(id)
                .password(password)
                .checkpassword(checkpassword)
                .nickname(nickname)
                .date(date)
                .build();
    }
}