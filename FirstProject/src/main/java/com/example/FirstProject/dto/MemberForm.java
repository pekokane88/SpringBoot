package com.example.FirstProject.dto;

import com.example.FirstProject.entity.Member;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MemberForm {

    private String email;

    private String password;

    public Member toEntity(){
        return new Member(null, email, password);
    }
}
