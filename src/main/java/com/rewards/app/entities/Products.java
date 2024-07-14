package com.rewards.app.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Products {
	@Id
	private String productId;
	private String productName;
	private BigDecimal price;
	private Integer tax;
	BigDecimal pointsValue;
	// specify the instance name used in child entity as the 'mappedBy'
	@OneToMany(mappedBy = "products", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<TransactionDetails> transactionDetails;
	private LocalDateTime expiryTime;
	private LocalDateTime manufacturingTime;
	private LocalDateTime createdTime;
	private LocalDateTime updatedTime;
}
