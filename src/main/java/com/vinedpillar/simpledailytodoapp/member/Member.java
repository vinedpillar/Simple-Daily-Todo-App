package com.vinedpillar.simpledailytodoapp.member;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Member {
    @Id
    private Long id;
    private String email;
    private String nickname;
    private String password;
}
