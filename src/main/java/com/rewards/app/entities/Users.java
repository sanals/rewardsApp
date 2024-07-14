package com.rewards.app.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
@Schema(description = "Entity Schema description for Users in entity")
public class Users {
	@Id
	@Schema(description = "Entity Unique Id in entity", example = "6992fa80-7428-497c-9546-1dd6ad8371c9")
	private String userId;
	@Schema(description = "Entity User Name in entity", example = "Sanal")
	private String userName;
	@Schema(description = "password in entity", example = "password...")
	private String password;
	private boolean enabled;
	private BigDecimal points;
	// specify the instance name used in child entity as the 'mappedBy'
	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<GiftCards> giftCards;
	private LocalDateTime createdTime;
	private LocalDateTime updatedTime;
}
