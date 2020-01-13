package com.hdmstuttgart.fluffybear.repository;

import java.util.List;

import com.hdmstuttgart.fluffybear.model.RecipeIngredientKey;
import org.springframework.data.repository.CrudRepository;

import com.hdmstuttgart.fluffybear.model.Ingredient;
import com.hdmstuttgart.fluffybear.model.Recipe;
import com.hdmstuttgart.fluffybear.model.RecipeIngredient;

public interface RecipeIngredientRepository extends CrudRepository<RecipeIngredient, RecipeIngredientKey> {
	List<RecipeIngredient> findByRecipe(Recipe recipe);
	List<RecipeIngredient> findByIngredient(Ingredient ingredient);
}
