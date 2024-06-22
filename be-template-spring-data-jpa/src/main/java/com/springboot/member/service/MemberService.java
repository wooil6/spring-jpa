package com.springboot.member.service;

import com.springboot.exception.BusinessLogicException;
import com.springboot.exception.ExceptionCode;
import com.springboot.member.entity.Member;
import com.springboot.member.repository.MemberRepository;
import org.apache.coyote.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class MemberService {
    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member createMember(Member member) {
        // TODO should business logic

        verifyExistsEmail(member.getEmail());

        return memberRepository.save(member);
    }

    public Member updateMember(Member member) {
        // TODO should business logic
        Member findMember = findVerifideMember(member.getMemberId());

        Optional.ofNullable(member.getName())
                .ifPresent(name -> findMember.setName(name));
        Optional.ofNullable(member.getPhone())
                .ifPresent(phone -> findMember.setPhone(phone));
        Optional.ofNullable(member.getMemberStatus())
                .ifPresent(memberStatus -> findMember.setMemberStatus(memberStatus));

        findMember.setModifiedAt(LocalDateTime.now());

        return memberRepository.save(findMember);

    }

    public Member findMember(long memberId) {
        // TODO should business logic
        return findVerifideMember(memberId);
    }

    public Page<Member> findMembers(int page, int size) {
        // TODO should business logic
        Pageable pageable = PageRequest.of(page, size, Sort.by("memberId").descending());
        return memberRepository.findAll(pageable);

//        return memberRepository.findAll(PageRequest.of(page, size,
//                Sort.by("memberId").descending()));
    }

    public void deleteMember(long memberId) {
        // TODO should business logic
        Member findMember = findVerifideMember(memberId);

//        findMember.setMemberStatus(Member.MemberStatus.MEMBER_QUIT);
//        memberRepository.save(findMember);

        memberRepository.delete(findMember);

    }

    public void verifyExistsEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);

        if (member.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
        }
    }

    public Member findVerifideMember(long memberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
//
//        Member findMember = optionalMember.orElseThrow(() ->
//                new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
//
//        return findMember;

        return optionalMember.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
    }
    
}
