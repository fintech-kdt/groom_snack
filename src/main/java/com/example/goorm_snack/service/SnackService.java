package com.example.goorm_snack.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.goorm_snack.model.*;
import com.example.goorm_snack.repository.*;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SnackService {

	private final SnackRepository snackRepository;
	private final SnackLikeRepository snackLikeRepository;
	private final SnackCommentRepository snackCommentRepository;
	private final MemberRepository memberRepository;

	public List<Snack> getAllSnacks() {
		return snackRepository.findAll();
	}

	public Snack getSnackById(Long id) {
		return snackRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid snack Id:" + id));
	}
	
	public Snack updateViewCount(Long id) {
		Snack snack = getSnackById(id);
		snack.setViewCount(snack.getViewCount() + 1);
		return snackRepository.save(snack);
	}

	public void addLike(Long snackId, String username) {
		Snack snack = getSnackById(snackId);
		Member member = memberRepository.findByUsername(username)
				.orElseThrow(() -> new IllegalArgumentException("Invalid username:" + username));

		if (snackLikeRepository.findBySnackAndMember(snack, member).isEmpty()) {
			SnackLike snackLike = new SnackLike();
			snackLike.setSnack(snack);
			snackLike.setMember(member);
			snackLikeRepository.save(snackLike);
		}
	}

	public void removeLike(Long snackId, String username) {
		Snack snack = getSnackById(snackId);
		Member member = memberRepository.findByUsername(username)
				.orElseThrow(() -> new IllegalArgumentException("Invalid username:" + username));

		snackLikeRepository.findBySnackAndMember(snack, member).ifPresent(snackLikeRepository::delete);
	}

	public void addComment(Long snackId, String username, String content) {
		Snack snack = getSnackById(snackId);
		Member member = memberRepository.findByUsername(username)
				.orElseThrow(() -> new IllegalArgumentException("Invalid username:" + username));

		SnackComment comment = new SnackComment();
		comment.setSnack(snack);
		comment.setMember(member);
		comment.setContent(content);
		snackCommentRepository.save(comment);
	}

	public List<Snack> getLikedSnacksByUsername(String username) {
		Member member = memberRepository.findByUsername(username)
				.orElseThrow(() -> new IllegalArgumentException("Invalid username:" + username));
		return snackLikeRepository.findSnacksByMember(member);
	}

	public Snack addSnack(String name, String taste, int inventoryCount) {
		Snack snack = new Snack();
		snack.setName(name);
		snack.setTaste(taste);
		snack.setInventoryCount(inventoryCount);
		snack.setViewCount(0);
		return snackRepository.save(snack);
	}

	public Snack updateSnack(Long id, String name, String taste, int inventoryCount) {
		Snack snack = getSnackById(id);
		snack.setName(name);
		snack.setTaste(taste);
		snack.setInventoryCount(inventoryCount);
		return snackRepository.save(snack);
	}

	public void deleteSnack(Long id) {
		snackRepository.deleteById(id);
	}
}