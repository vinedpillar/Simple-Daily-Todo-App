package com.vinedpillar.simpledailytodoapp.member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class MemberDto {
    // Post용 DTO
    @Getter
    public static class Post {
        @NotBlank
        @Email
        private String email;
        private String password;
        @NotBlank(message = "닉네임은 공백일 수 없습니다.")
        private String nickname;
    }

    // Patch용 DTO
    @Getter
    public static class Patch {
        private Long id;
        private String email;
        private String password;
        private String nickname;

        public void setMemberId(Long id) {
            this.id = id;
        }
    }

    // Response용 DTO
    @Getter
    public static class Response {
        private Long id;
        private String email;
        private String password;
        private String nickname;
    }
}
