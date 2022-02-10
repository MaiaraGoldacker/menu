package com.hexagonal.menu.adapters.outbound.persistence;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hexagonal.menu.adapters.outbound.persistence.entities.MealEntity;

public interface MealJpaRepository extends JpaRepository<MealEntity, UUID>{

}
