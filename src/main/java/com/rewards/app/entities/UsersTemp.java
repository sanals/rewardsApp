package com.rewards.app.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
public class UsersTemp {
	@Id
	private String userId;
	private String userName;
	private String password;
	private boolean enabled;
	private BigDecimal points;
	// specify the instance name used in child entity as the 'mappedBy'
	@OneToMany(mappedBy = "usersTemp", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<GiftCards> giftCards;
	private LocalDateTime createdTime;
	private LocalDateTime updatedTime;
}
