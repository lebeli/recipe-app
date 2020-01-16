package com.hdmstuttgart.fluffybear.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.hdmstuttgart.fluffybear.model.Ingredient;

/**
 * Spring boot repository for Ingredients which extends a CrudRepository
 */
public interface IngredientRepository  extends CrudRepository<Ingredient, Long> {
	/**
	 * Returns Ingredient identified by unique id in the connected database.
	 * @param id which is used for identifying the ingredient.
	 * @return Ingredient identified by id.
	 */
	Ingredient findById(long id);

	/**
	 * Returns Ingredients identified by name in the connected database.
	 * @param name of the ingredient.
	 * @return list of all ingredients which matched the name.
	 */
	List<Ingredient> findByName(String name);
}
