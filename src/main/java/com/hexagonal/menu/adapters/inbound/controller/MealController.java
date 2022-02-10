package com.hexagonal.menu.adapters.inbound.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.hexagonal.menu.core.domain.MealDomain;
import com.hexagonal.menu.core.domain.PageInfoDomain;
import com.hexagonal.menu.core.ports.MealServicePort;

import lombok.AllArgsConstructor;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@AllArgsConstructor
@RequestMapping("/meal")
public class MealController {
	
	@Autowired
	final MealServicePort menuServicePort;
	
	@GetMapping
	public ResponseEntity<Page<MealDomain>> getAllMeals(@PageableDefault(page = 0, size = 10, sort = "id", 
														direction = Sort.Direction.ASC) Pageable pageable) {
		var pageInfo = new PageInfoDomain();
	    BeanUtils.copyProperties(pageable, pageInfo);
	    List<MealDomain> list = menuServicePort.findAllMeals(pageInfo);
	    return ResponseEntity.status(HttpStatus.OK).body(new PageImpl<MealDomain>(list, pageable, list.size()));
	}
	
	@PostMapping
	public ResponseEntity<MealDomain> save(@RequestBody @Valid @JsonView(MealDomain.MealView.MealInput.class) MealDomain mealDomain) {
		return ResponseEntity.status(HttpStatus.CREATED).body(menuServicePort.save(mealDomain));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<MealDomain> update(@RequestBody @Valid @JsonView(MealDomain.MealView.MealInput.class) MealDomain mealDomain, 
											 @PathVariable UUID id) {
		return ResponseEntity.status(HttpStatus.OK).body(menuServicePort.update(mealDomain, id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<MealDomain> delete(@PathVariable UUID id) {
		menuServicePort.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
