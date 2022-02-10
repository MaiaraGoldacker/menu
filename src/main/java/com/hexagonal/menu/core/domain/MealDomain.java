package com.hexagonal.menu.core.domain;

import java.math.BigDecimal;
import java.util.UUID;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonView;
import com.hexagonal.menu.core.domain.enums.TypeOfMeal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MealDomain {

	public interface MealView {
		public static interface MealInput {}
		public static interface MealComplete {}		
	}
	
	@JsonView(MealView.MealComplete.class)
	private UUID id;
	
	@JsonView({MealView.MealComplete.class, MealView.MealInput.class})
	@NotEmpty
	private String description;
	
	@JsonView({MealView.MealComplete.class, MealView.MealInput.class})
	@NotNull
	private BigDecimal value;
	
	@JsonView({MealView.MealComplete.class, MealView.MealInput.class})
	private String note;
	
	@JsonView({MealView.MealComplete.class, MealView.MealInput.class})
	@NotNull
	private TypeOfMeal type;	
}
