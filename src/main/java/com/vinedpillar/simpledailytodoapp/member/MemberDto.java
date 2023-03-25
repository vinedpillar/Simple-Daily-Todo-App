package com.vinedpillar.simpledailytodoapp.member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class MemberDto {
    @Getter
    public static class Post {
        @NotBlank
        @Email
        private String email;
        private String password;
        @NotBlank(message = "닉네임은 공백일 수 없습니다.")
        private String nickname;
    }

    @Getter
    public static class Patch {
        private Long id;
        private String email;
        private String password;
        private String nickname;
    }

    @Getter
    public static class Response {
        private Long id;
        private String email;
        private String password;
        private String nickname;
    }
}
