package com.vinedpillar.simpledailytodoapp.memo;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemoMapper {
    // 메모 포스트 -> 메모
    // 메모 패치 -> 메모
    // 메모 -> 메모 반응
    // 메모들 -> 메모 리스트
}
