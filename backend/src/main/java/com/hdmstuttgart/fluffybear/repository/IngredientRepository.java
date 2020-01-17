package com.hdmstuttgart.fluffybear.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.hdmstuttgart.fluffybear.model.Ingredient;

/**
 * Spring boot repository for Ingredients which extends a CrudRepository
 */
public interface IngredientRepository  extends CrudRepository<Ingredient, Long> {
	/**
	 * Returns Ingredients identified by name in the connected database.
	 *
	 * @param id  name of the ingredient.
	 * @return list of all ingredients which matched the name.
	 */
	Ingredient findById(String id);
}
