package com.example.goorm_snack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.goorm_snack.model.Snack;

public interface SnackRepository extends JpaRepository<Snack, Long> {
}