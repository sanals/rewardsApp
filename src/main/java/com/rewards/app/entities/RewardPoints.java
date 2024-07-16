package com.rewards.app.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class RewardPoints {
	@Id
	private String rewardPointId;
	private Integer totslPointsGained;
	private Integer remainingPoints;
	@OneToOne
	// specify the primary key as 'name'
	@JoinColumn(name = "userId", nullable = false)
	private UsersTemp usersTemp;
	private LocalDateTime createdTime;
	private LocalDateTime updatedTime;

}
