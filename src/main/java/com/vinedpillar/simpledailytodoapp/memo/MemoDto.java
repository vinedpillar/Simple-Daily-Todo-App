package com.vinedpillar.simpledailytodoapp.memo;

import lombok.Getter;

import java.time.LocalDateTime;

public class MemoDto {
    // 포스트용 클래스
    @Getter
    public static class Post {
        private String memoContent;
    }

    // 패치용 클래스
    @Getter
    public static class Patch {
        private Long id;
        private String memoContent;
    }

    // 반응용 클래스
    @Getter
    public static class Response {
        private Long id;
        private String memoContent;
        private LocalDateTime createDate;
        private LocalDateTime updateDate;
    }
}
