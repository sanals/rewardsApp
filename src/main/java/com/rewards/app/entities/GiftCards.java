package com.rewards.app.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class GiftCards {
	@Id
	private String giftCardId;
	private Integer points;
	private Integer remainingPoints;
	@ManyToOne
	// specify the primary key as 'name'
	@JoinColumn(name = "userId", nullable = false)
	private UsersTemp usersTemp;
	private LocalDateTime createdTime;
	private LocalDateTime expiryTime;
}
