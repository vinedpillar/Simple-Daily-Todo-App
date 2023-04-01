package com.vinedpillar.simpledailytodoapp.memo;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemoMapper {
    // 메모 포스트 -> 메모
    Memo memoPostToMemo(MemoDto.Post requestBody);

    // 메모 패치 -> 메모
    Memo memoPatchToMemo(MemoDto.Patch requestBody);

    // 메모 -> 메모 반응
    MemoDto.Response memoToMemoResponse(Memo memo);

    // 메모들 -> 메모 리스트
    List<MemoDto.Response> memosToMemoResponseList(List<Memo> memoList);
}
