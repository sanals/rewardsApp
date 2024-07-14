package com.rewards.app.form;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

public class ProductsForm {
	@NotBlank
	@Size(min = 2, max = 16)
	private String productName;
	@Min(value = 0)
	@Max(value = 10000000)
	private BigDecimal price;
	@Min(value = 0)
	@Max(value = 1000)
	private Integer tax;
	@Min(value = 0)
	@Max(value = 1000)
	BigDecimal pointsMultiplier;
	@FutureOrPresent
//	@Pattern(regexp = "^(\\d{4})-([01]\\d|2[0-3])-([01]\\d|2[0-9]|3[01]) ([01][0-9]|2[0-3]):[0-5][0-9]$",
//    message = "Invalid date and time format (YYYY-MM-DD HH:mm)")
	private LocalDateTime expiryTime;
	@PastOrPresent
	private LocalDateTime manufacturingTime;
}
