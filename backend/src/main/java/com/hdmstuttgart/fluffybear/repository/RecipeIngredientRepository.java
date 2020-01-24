package com.hdmstuttgart.fluffybear.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.hdmstuttgart.fluffybear.model.Ingredient;
import com.hdmstuttgart.fluffybear.model.Recipe;
import com.hdmstuttgart.fluffybear.model.RecipeIngredient;

/**
 * Spring boot repository for RecipeIngredient which extends a CrudRepository
 */
public interface RecipeIngredientRepository extends CrudRepository<RecipeIngredient, Long> {
	List<RecipeIngredient> findByRecipe(Recipe recipe);
	List<RecipeIngredient> findByIngredient(Ingredient ingredient);
}
