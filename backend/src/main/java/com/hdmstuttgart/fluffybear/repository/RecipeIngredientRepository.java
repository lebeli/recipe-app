package com.hdmstuttgart.fluffybear.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.hdmstuttgart.fluffybear.model.Ingredient;
import com.hdmstuttgart.fluffybear.model.Recipe;
import com.hdmstuttgart.fluffybear.model.RecipeIngredient;

public interface RecipeIngredientRepository extends CrudRepository<RecipeIngredient, Long> {
	
	RecipeIngredient findById(long id);
	List<RecipeIngredient> findByRecipe(Recipe recipe);
	List<RecipeIngredient> findByIngredient(Ingredient ingredient);
}
