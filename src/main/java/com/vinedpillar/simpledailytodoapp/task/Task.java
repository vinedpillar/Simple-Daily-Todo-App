package com.vinedpillar.simpledailytodoapp.task;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Task {
    // 고유 번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 내용
    @Column
    private String content;

    // 작성일
    @Column
    private LocalDateTime createDate;

    // 진행 예정 시간(옵션)
    @Column
    private LocalDateTime scheduleTime;

    // 완료일
    @Column
    private LocalDateTime completeDate;
}
