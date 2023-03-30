package com.vinedpillar.simpledailytodoapp.memo;

import com.vinedpillar.simpledailytodoapp.exception.BusinessLogicException;
import com.vinedpillar.simpledailytodoapp.exception.ExceptionCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemoService {
    private final MemoRepository memoRepository;

    public MemoService(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    // 댓글 생성
    public Memo createMemo(Memo memo) {

        return memoRepository.save(memo);
    }

    // 댓글 조회
    public Memo findMemo(Long memoId) {

        return findVerifiedMemo(memoId);
    }

    // 전체 댓글 조회
    public Page<Memo> findMemoList(int page, int size) {

        return memoRepository.findAll(PageRequest
                .of(page, size, Sort.by("Id").descending()));
    }

    // 댓글 수정
    public Memo updateMemo(Memo memo) {
        Memo foundMemo = findVerifiedMemo(memo.getId());

        Optional.ofNullable(memo.getMemoContent())
                .ifPresent(memoContent -> foundMemo.setMemoContent(memoContent));

        return memoRepository.save(foundMemo);
    }

    // 댓글 삭제
    public void deleteMemo(Long memoId) {
        Memo checkedMemo = findVerifiedMemo(memoId);
        memoRepository.delete(checkedMemo);
    }

    // 메모 확인
    private Memo findVerifiedMemo(Long memoId) {
        Optional<Memo> optionalMemo = memoRepository.findById(memoId);
        Memo foundMemo = optionalMemo.orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.TASK_NOT_FOUND));

        return foundMemo;
    }
}
