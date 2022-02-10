package com.hexagonal.menu.core.ports.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import com.hexagonal.menu.core.domain.MealDomain;
import com.hexagonal.menu.core.domain.PageInfoDomain;
import com.hexagonal.menu.core.domain.enums.TypeOfMeal;
import com.hexagonal.menu.core.ports.MealPersistencePort;
import com.hexagonal.menu.core.ports.MealServicePort;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MealServicePortImpl implements MealServicePort {

	@Autowired
	final MealPersistencePort mealPersistencePort;
	
	@Override
	public List<MealDomain> findAllMeals(PageInfoDomain pageInfo) {		
		return mealPersistencePort.findAllMeals(pageInfo);
	}

	@Override
	public MealDomain save(MealDomain mealDomain) {
		validDessert(mealDomain);
		return mealPersistencePort.save(mealDomain);
	}

	@Override
	public MealDomain update(MealDomain mealDomain, UUID id) {
		validDessert(mealDomain);
		return mealPersistencePort.update(mealDomain, id);
	}
	
	@Override
	public void delete(UUID id) {
		mealPersistencePort.delete(id);
	}
	
	private void validDessert(MealDomain mealDomain) {
		if (mealDomain.getNote() == null || 
				mealDomain.getNote().isEmpty()) {
			if (mealDomain.getType() == TypeOfMeal.DESSERT) {
				mealDomain.setNote("Sobremesa completa");
			}
		}
	}

}
