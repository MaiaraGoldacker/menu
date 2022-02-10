package com.hexagonal.menu.adapters.outbound.persistence;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.hexagonal.menu.adapters.outbound.persistence.entities.MealEntity;
import com.hexagonal.menu.core.domain.MealDomain;
import com.hexagonal.menu.core.domain.PageInfoDomain;
import com.hexagonal.menu.core.ports.MealPersistencePort;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class MealPersistencePortImpl implements MealPersistencePort {

	@Autowired
	final MealJpaRepository mealJpaRepository;
	
	@Autowired
	final ModelMapper modelMapper;
	
	@Override
	public List<MealDomain> findAllMeals(PageInfoDomain pageInfoDto) {
		Pageable pageable = PageRequest.of(pageInfoDto.getPageNumber(), pageInfoDto.getPageSize());
	       return mealJpaRepository.findAll(pageable).stream().map(entity -> modelMapper.map(entity, MealDomain.class))
	                .collect(Collectors.toList());
	}

	@Override
	public MealDomain save(MealDomain mealDomain) {
		return modelMapper.map(mealJpaRepository.save(modelMapper.map(mealDomain, MealEntity.class)), MealDomain.class);
	}

	@Override
	public MealDomain update(MealDomain mealDomain, UUID id) {
		var mealOptional = mealJpaRepository.findById(id);

		if (mealOptional.isPresent()) {
			var meal = mealOptional.get();
			meal.setDescription(mealDomain.getDescription());
			meal.setNote(mealDomain.getNote());
			meal.setValue(mealDomain.getValue());
			meal.setType(mealDomain.getType());
			return modelMapper.map(mealJpaRepository.save(meal), MealDomain.class);
		}
		
		return null;
	}
	
	@Override
	public void delete(UUID id) {
		mealJpaRepository.deleteById(id);
	}
}
