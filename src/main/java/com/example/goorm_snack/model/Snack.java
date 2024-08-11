package com.example.goorm_snack.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Snack {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, unique = true)
	private String name;
	@Column(nullable = false)
	private String taste;
	private int inventoryCount;
	private int viewCount;
	
    @OneToMany(mappedBy = "snack", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SnackLike> likes = new ArrayList<>();
    
    @OneToMany(mappedBy = "snack", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SnackComment> comments = new ArrayList<>();
}
