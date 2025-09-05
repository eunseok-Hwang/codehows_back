package com.back.service;

import com.back.entity.Member;
import com.back.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Optional<Member> member = memberRepository.findByUserId(userId);

        UserDetails userDetails = null;

        if (member.isPresent()) {
            Member members = member.get();
            userDetails = User.withUsername(userId)
                    .password(members.getPassword())
                    .roles(String.valueOf(members.getRole()))
                    .build();

        }
        else{
            throw new UsernameNotFoundException("No user found with member id ");
        }

        return userDetails;
    }
}
