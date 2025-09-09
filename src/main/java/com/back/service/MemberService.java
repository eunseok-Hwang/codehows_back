package com.back.service;

import com.back.constant.Role;
import com.back.dto.MemberDto;
import com.back.dto.MemberInfoDto;
import com.back.entity.Member;
import com.back.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member saveMember(MemberDto memberDto) {
        Member member = Member.builder()
                .userId(memberDto.getUserId())
                .password(memberDto.getPassword())
                .nickname(memberDto.getNickname())
                .username(memberDto.getUsername())
                .role(Role.USER)
                .build();
        return memberRepository.save(member);
    }

    public MemberInfoDto findMemberByUserId(String userId) {
        Member member = memberRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        MemberInfoDto memberInfoDto = MemberInfoDto.builder()
                .memberId(member.getUserId())
                .nickname(member.getNickname())
                .build();
        return memberInfoDto;
    }
}
