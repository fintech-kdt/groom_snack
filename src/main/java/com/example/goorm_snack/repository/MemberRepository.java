package com.example.goorm_snack.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.goorm_snack.model.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(String username);
    boolean existsByUsername(String username);
}