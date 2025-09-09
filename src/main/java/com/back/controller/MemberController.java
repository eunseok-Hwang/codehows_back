package com.back.controller;

import com.back.dto.AccountCredentials;
import com.back.dto.MemberDto;
import com.back.dto.MemberInfoDto;
import com.back.entity.Member;
import com.back.service.JwtService;
import com.back.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public void createMember(@RequestBody MemberDto memberDto) {
        memberService.saveMember(memberDto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AccountCredentials credentials) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(
                       credentials.getUserId(), credentials.getPassword());

        Authentication authentication = authenticationManager.authenticate(token);
        MemberInfoDto memberInfoDto = memberService.findMemberByUserId(authentication.getName());
        String jwtToken = jwtService.generateToken(authentication.getName());
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken);


        return new ResponseEntity<>(memberInfoDto, headers, HttpStatus.OK);
    }




}
