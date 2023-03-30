package com.vinedpillar.simpledailytodoapp.exception;

import lombok.Getter;

public enum ExceptionCode {
    MEMBER_NOT_FOUND(404, "Member Not Found"),
    MEMBER_EXISTS(409, "Member Exists"),
    TASK_NOT_FOUND(404, "Task Not Found"),
    TASK_EXISTS(409, "Task Exists"),
    MEMO_NOT_FOUND(404, "Memo Not Found"),
    MEMO_EXISTS(409, "Memo Exists");

    @Getter
    private int statusCode;
    @Getter
    private String message;

    ExceptionCode(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
