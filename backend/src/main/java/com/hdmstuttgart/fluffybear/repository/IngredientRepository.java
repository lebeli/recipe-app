package com.hdmstuttgart.fluffybear.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.hdmstuttgart.fluffybear.model.Ingredient;

public interface IngredientRepository  extends CrudRepository<Ingredient, Long> {
	Ingredient findById(String id);
}
