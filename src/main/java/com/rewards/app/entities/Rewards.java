package com.rewards.app.entities;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Rewards{
	@Id
	private String rewardId;
	@ManyToOne
	@JoinColumn(name = "userId", nullable = false)
	private Users user;
	@ManyToOne
	@JoinColumn(name = "rewardPointsId", nullable = true)
	private RewardPoints rewardPoints;
	private Integer redeemedRewardPoints;
	@ManyToOne
	@JoinColumn(name = "giftCardsId", nullable = true)
	private GiftCards giftCards;
	private Integer redeemedGiftCardsPoints;
	@OneToOne
	@JoinColumn(name = "transactionsId", nullable = false)
	private Transactions transactions;
	private BigDecimal pointsMultiplier;
	private BigDecimal redeemedAmount;
	
}
