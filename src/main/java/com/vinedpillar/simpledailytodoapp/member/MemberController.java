package com.vinedpillar.simpledailytodoapp.member;

import com.vinedpillar.simpledailytodoapp.dto.MultiResponseDto;
import com.vinedpillar.simpledailytodoapp.dto.SingleResponseDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/members")
@Validated
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper memberMapper;

    public MemberController(MemberService memberService, MemberMapper memberMapper) {
        this.memberService = memberService;
        this.memberMapper = memberMapper;
    }

    // 회원 등록 요청
    @PostMapping
    public ResponseEntity postMember(@RequestBody @Valid MemberDto.Post requestBody) {
        Member member = memberMapper.memberPostToMember(requestBody);
        Member createdMember = memberService.createMember(member);

        return new ResponseEntity<>(
                new SingleResponseDto<>(memberMapper.memberToMemberResponse(createdMember)), HttpStatus.CREATED);
    }

    // 회원 조회 요청
    @GetMapping("/{member-id}")
    public ResponseEntity getMember(@PathVariable("member-id") @Positive Long memberId) {
        Member getMember = memberService.findMember(memberId);

        return new ResponseEntity<>(
                new SingleResponseDto<>(
                        memberMapper.memberToMemberResponse(getMember)), HttpStatus.OK);
    }

    // 회원 목록 조회 요청
    @GetMapping
    public ResponseEntity getMembers(@RequestParam @Positive int page,
                                     @RequestParam @Positive int size) {
        Page<Member> pageMembers = memberService.findMembers(page - 1, size);
        List<Member> listMember = pageMembers.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(
                        memberMapper.membersToMemberResponseList(listMember), pageMembers), HttpStatus.OK);
    }

    // 회원 정보 수정 요청
    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(@PathVariable("member-id") @Positive Long memberId,
                                      @RequestBody @Valid MemberDto.Patch requestBody) {
        requestBody.setMemberId(memberId);
        Member member = memberService.updateMember(
                memberMapper.memberPatchToMember(requestBody));

        return new ResponseEntity<>(
                new SingleResponseDto<>(
                        memberMapper.memberToMemberResponse(member)), HttpStatus.OK);
    }

    // 회원 삭제 요청
    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(@PathVariable("member-id") @Positive Long memberId) {
        memberService.memberDelete(memberId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
