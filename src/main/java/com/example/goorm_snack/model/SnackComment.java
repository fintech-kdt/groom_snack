package com.example.goorm_snack.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class SnackComment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 500, nullable = false)
	private String content;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="snack_id", nullable = false)
	private Snack snack;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="member_id", nullable = false)
	private Member member;
}
