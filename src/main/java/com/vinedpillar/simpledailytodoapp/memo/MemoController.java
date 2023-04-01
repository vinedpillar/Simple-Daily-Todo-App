package com.vinedpillar.simpledailytodoapp.memo;

import com.vinedpillar.simpledailytodoapp.dto.SingleResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class MemoController {
    private final MemoService memoService;
    private final MemoMapper memoMapper;

    public MemoController(MemoService memoService, MemoMapper memoMapper) {
        this.memoService = memoService;
        this.memoMapper = memoMapper;
    }

    // 댓글 생성 요청
    @PostMapping
    public ResponseEntity postMemo(@RequestBody MemoDto.Post requestBody) {
        Memo memo = memoMapper.memoPostToMemo(requestBody);
        Memo createMemo = memoService.createMemo(memo);

        return new ResponseEntity<>(
                new SingleResponseDto<>(memoMapper.memoPostToMemo(createMemo)), HttpStatus.CREATED);
    }


    // 댓글 조회 요청
    @GetMapping


    // 전체 댓글 조회 요청
    @GetMapping


    // 댓글 수정 요청
    @PatchMapping


    // 댓글 삭제 요청
    @DeleteMapping("/{memo-id}")

}
