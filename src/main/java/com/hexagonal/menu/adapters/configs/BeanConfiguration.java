package com.hexagonal.menu.adapters.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import com.hexagonal.menu.MenuApplication;
import com.hexagonal.menu.core.ports.MealPersistencePort;
import com.hexagonal.menu.core.ports.service.MealServicePortImpl;

@Configuration
@ComponentScan(basePackageClasses = MenuApplication.class)
public class BeanConfiguration {
	
	@Bean
	MealServicePortImpl mealServicePortImpl(MealPersistencePort persistence) {
		return new MealServicePortImpl(persistence);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
