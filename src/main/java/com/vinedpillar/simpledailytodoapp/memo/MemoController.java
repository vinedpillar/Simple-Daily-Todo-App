package com.vinedpillar.simpledailytodoapp.memo;

import com.vinedpillar.simpledailytodoapp.dto.MultiResponseDto;
import com.vinedpillar.simpledailytodoapp.dto.SingleResponseDto;
import jakarta.websocket.server.PathParam;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/memos")
@Validated
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
        Memo createdMemo = memoService.createMemo(memo);

        return new ResponseEntity<>(
                new SingleResponseDto<>(memoMapper.memoToMemoResponse(createdMemo)), HttpStatus.CREATED);
    }

    // 댓글 조회 요청
    @GetMapping("/{memo-id}")
    public ResponseEntity getMemo(@PathVariable("memo-id") Long memoId) {
        Memo memo = memoService.findMemo(memoId);

        return new ResponseEntity<>(
                new SingleResponseDto<>(memoMapper.memoToMemoResponse(memo)),
                HttpStatus.OK);
    }

    // 전체 댓글 조회 요청
    @GetMapping
    public ResponseEntity getMemoList(@RequestParam int page,
                                      @RequestParam int size) {
        Page<Memo> pageMemos = memoService.findMemoList(page - 1, size);
        List<Memo> memoList = pageMemos.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(memoMapper.memosToMemoResponseList(memoList), pageMemos),
                HttpStatus.OK);
    }

    // 댓글 수정 요청
    @PatchMapping("/{memo-id}")
    public ResponseEntity patchMemo(@PathVariable("memo-id") Long memoId,
                                    @RequestBody MemoDto.Patch requestBody) {
        requestBody.setMemoId(memoId);
        Memo memo = memoService.updateMemo(memoMapper.memoPatchToMemo(requestBody));

        return new ResponseEntity<>(
                new SingleResponseDto<>(memoMapper.memoToMemoResponse(memo)),
                HttpStatus.OK);
    }


    // 댓글 삭제 요청
    @DeleteMapping("/{memo-id}")
    public ResponseEntity deleteMemo(@PathParam("memo-id") Long memoId) {
        memoService.deleteMemo(memoId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
