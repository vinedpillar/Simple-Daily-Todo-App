package com.vinedpillar.simpledailytodoapp.exception;

import lombok.Getter;

public enum ExceptionCode {
    MEMBER_NOT_FOUND(404, "Member not found"),
    MEMBER_EXISTS(409, "Member exists"),
    TASK_NOT_FOUND(404, "Task not found"),
    TASK_EXISTS(409, "Task exists");

    @Getter
    private int statusCode;
    @Getter
    private String message;

    ExceptionCode(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
