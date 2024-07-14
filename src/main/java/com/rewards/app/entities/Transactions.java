package com.rewards.app.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Transactions {
	@Id
	private String transactionId;
	private LocalDateTime transactionTime;
	@ManyToOne
	// specify the primary key as 'name'
	@JoinColumn(name = "userId", nullable = false)
	private Users users;
	// specify the instance name used in child entity as the 'mappedBy'
	@OneToMany(mappedBy = "transactions", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<TransactionDetails> transactionDetails;
	private BigDecimal totalAmout;
	private Double totalTax;
	@OneToOne
	@JoinColumn(name = "rewardsId", nullable = false)
	private Rewards rewards;
	private BigDecimal redeemedAmount;
	private BigDecimal amountPayable;
	private LocalDateTime createdTime;
	private LocalDateTime updatedTime;
}
