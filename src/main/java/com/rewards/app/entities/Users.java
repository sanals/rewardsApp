package com.rewards.app.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Users {
	@Id
	@Column()
	private String username;
	private String password;
	private boolean enabled;
	private BigDecimal points;
	// specify the instance name used in child entity as the 'mappedBy'
	private LocalDateTime createdTime;
	private LocalDateTime updatedTime;
}
