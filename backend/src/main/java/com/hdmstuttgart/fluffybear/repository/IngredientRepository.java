package com.hdmstuttgart.fluffybear.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.hdmstuttgart.fluffybear.model.Ingredient;

/**
 * Spring boot repository for Ingredients which extends a CrudRepository
 */
public interface IngredientRepository  extends CrudRepository<Ingredient, String> {
	/**
	 * Returns Ingredients identified by name in the connected database.
	 *
	 * @param id  name of the ingredient.
	 * @return list of all ingredients which matched the name.
	 */
	 Optional<Ingredient> findById(String id);
}
