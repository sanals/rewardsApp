package com.rewards.app.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class TransactionDetails {
	@Id
	private String transactionDetailId;
	@ManyToOne
	// specify the primary key as 'name'
	@JoinColumn(name = "transactionId", nullable = false)
	private Transactions transactions;
	private Integer quantity;
	@ManyToOne
	// specify the primary key as 'name'
	@JoinColumn(name = "productId", nullable = false)
	private Products products;
	private BigDecimal totalAmount;
	private Double totalTax;
	private LocalDateTime createdTime;
	private LocalDateTime updatedTime;
}
