package com.vinedpillar.simpledailytodoapp.member;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/members")
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper memberMapper;

    public MemberController(MemberService memberService, MemberMapper memberMapper) {
        this.memberService = memberService;
        this.memberMapper = memberMapper;
    }

    // 회원 등록 요청
    @PostMapping
    public ResponseEntity postMember() {
        //Member member = memberMapper;

        //return ResponseEntity.created();
    }
    // 회원 조회 요청
    // 회원 목록 조회 요청
    // 회원 정보 수정 요청
    // 회원 삭제 요청
}
