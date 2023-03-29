package com.vinedpillar.simpledailytodoapp.task;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

public class TaskDto {
    // 포스트 DTO
    @Getter
    @AllArgsConstructor
    public static class Post {
        private String content;
    }

    // 패치 DTO
    @Getter
    @AllArgsConstructor
    public static class Patch {
        private String content;
    }

    // 응답 DTO
    @Getter
    @AllArgsConstructor
    public static class Response {
        private Long id;
        private String content;
        private LocalDateTime createDate;
        private LocalDateTime scheduleTime;
        private LocalDateTime completeDate;
    }
}
