package com.hexagonal.menu.core.ports;

import java.util.List;
import java.util.UUID;

import com.hexagonal.menu.core.domain.MealDomain;
import com.hexagonal.menu.core.domain.PageInfoDomain;

public interface MealServicePort {
	
	List<MealDomain> findAllMeals(PageInfoDomain pageable);
	
	MealDomain save(MealDomain mealDomain);
	
	MealDomain update(MealDomain mealDomain, UUID id);
	
	void delete(UUID id);
}
