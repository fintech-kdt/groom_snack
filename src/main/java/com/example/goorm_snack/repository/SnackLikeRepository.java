package com.example.goorm_snack.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.goorm_snack.model.Member;
import com.example.goorm_snack.model.Snack;
import com.example.goorm_snack.model.SnackLike;

public interface SnackLikeRepository extends JpaRepository<SnackLike, Long> {

	Optional<SnackLike> findBySnackAndMember(Snack snack, Member member);

    @Query("SELECT sl.snack FROM SnackLike sl WHERE sl.member = :member")
    List<Snack> findSnacksByMember(@Param("member") Member member);
}