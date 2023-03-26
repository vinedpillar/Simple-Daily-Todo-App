package com.vinedpillar.simpledailytodoapp.member;

import com.vinedpillar.simpledailytodoapp.exception.BusinessLogicException;
import com.vinedpillar.simpledailytodoapp.exception.ExceptionCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원 등록
    public Member createMember(Member member) {
        verifyExistsEmail(member.getEmail());
        Member savedMember = memberRepository.save(member);

        return savedMember;
    }

    // 회원 조회
    public Member findMember(Long memberId) {

        return findVerifiedMember(memberId);
    }

    // 회원 목록 조회
    public Page<Member> findMembers(int page, int size) {

        return memberRepository.findAll(PageRequest.of(
                page, size, Sort.by("memberId").descending()));
    }

    // 회원 정보 수정
    public Member updateMember(Member member) {
        Member findMember = findVerifiedMember(member.getId());

        Optional.ofNullable(member.getNickname())
                .ifPresent(nickname -> findMember.setNickname(nickname));

        return memberRepository.save(findMember);
    }

    // 회원 삭제
    public void memberDelete(Long memberId) {
        Member findMember = findVerifiedMember(memberId);
        memberRepository.delete(findMember);
    }

    // 회원 정보 검증
    public Member findVerifiedMember(Long memberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Member findMember = optionalMember.orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        return findMember;
    }

    // 이메일 정보 검증
    public void verifyExistsEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if(member.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
        }

    }

}
