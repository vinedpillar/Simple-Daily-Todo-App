package com.vinedpillar.simpledailytodoapp.member;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapper {
    // 멤버 포스트 -> 멤버
    Member memberPostToMember(MemberDto.Post requestBody);

    // 멤버 패치 -> 멤버
    Member memberPatchToMember(MemberDto.Patch requestBody);

    // 멤버 -> 멤버 응답
    MemberDto.Response memberToMemberResponse(Member member);

    // 멤버들 -> 멤버 응답 리스트
    List<MemberDto.Response> membersToMemberResponseList(List<Member> members);
}
