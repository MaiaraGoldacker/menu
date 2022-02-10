package com.hexagonal.menu.adapters.outbound.persistence.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hexagonal.menu.core.domain.enums.TypeOfMeal;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "MENU")
public class MealEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
    
    @Column(nullable = false, length = 150)
	private String description;
    
    @Column(nullable = false)
	private BigDecimal value;
    
    @Column(length = 500)
	private String note;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
	private TypeOfMeal type;
}
