package com.example.goorm_snack.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.goorm_snack.model.Member;
import com.example.goorm_snack.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void join(String username, String password) {
        if (memberRepository.existsByUsername(username)) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

        Member member = new Member();
        member.setUsername(username);
        member.setPassword(passwordEncoder.encode(password));
        member.setRole("ROLE_USER");

        memberRepository.save(member);
    }
}
