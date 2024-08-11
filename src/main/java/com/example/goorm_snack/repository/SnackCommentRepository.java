package com.example.goorm_snack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.goorm_snack.model.SnackComment;

public interface SnackCommentRepository extends JpaRepository<SnackComment, Long> {
}