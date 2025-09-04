package com.back.service;

import com.back.constant.Role;
import com.back.dto.MemberDto;
import com.back.entity.Member;
import com.back.repository.MemberRepository;
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
                .user_id(memberDto.getUser_id())
                .password(memberDto.getPassword())
                .nickname(memberDto.getNickname())
                .username(memberDto.getUsername())
                .role(Role.USER)
                .build();
        return memberRepository.save(member);
    }
}
