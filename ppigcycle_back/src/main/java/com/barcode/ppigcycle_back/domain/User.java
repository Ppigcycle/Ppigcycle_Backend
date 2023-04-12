package com.barcode.ppigcycle_back.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="User")
@AllArgsConstructor
@Builder
public class User extends BaseTimeEntity {

    @Id
    @Column(name="user_id", unique = true)
    private String id; // 아이디

    @Column(name = "user_pw", unique = true)
    private String password; // 비밀번호

    @Column(name = "user_checkpw")
    private String checkpassword; // 비밀번호 확인

    @Column(name = "user_nickname", unique = true, length = 20)
    private String nickname; // 닉네임

    private String date; // 분리수거 버리는 날 지정

}
