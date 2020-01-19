package com.hdmstuttgart.fluffybear.service;

import java.util.ArrayList;
import java.util.List;

import com.hdmstuttgart.fluffybear.model.RecipeIngredientKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdmstuttgart.fluffybear.model.Ingredient;
import com.hdmstuttgart.fluffybear.model.Recipe;
import com.hdmstuttgart.fluffybear.model.RecipeIngredient;
import com.hdmstuttgart.fluffybear.repository.RecipeIngredientRepository;

/**
 * Spring boot service for ingredients related to recipes.
 */
@Service
public class RecipeIngredientService {

	/**
	 * Repository for ingredients related to a recipe.
	 */
	@Autowired
	RecipeIngredientRepository recipeIngredientRepository;

	/**
	 * Returns all ingredients related to recipes from the repository in a simple java list.
	 *
	 * @return the recipeIngredient list
	 */
	public List<RecipeIngredient> getAllRecipeIngredients() {
		List<RecipeIngredient> recipeIngredients = new ArrayList<RecipeIngredient>();
		recipeIngredientRepository.findAll().forEach(recipeIngredient -> {
			recipeIngredients.add(recipeIngredient);
		});
		return recipeIngredients;
	}

	/**
	 * Get recipeIngredient list by a given recipe.
	 *
	 * @param recipe which is related to different recipeIngredient objects.
	 * @return recipeIngredients in a list.
	 */
	public List<RecipeIngredient> getRecipeIngredient(Recipe recipe) {
		return recipeIngredientRepository.findByRecipe(recipe);
	}

	/**
	 * Get recipeIngredient list by a given ingredient.
	 *
	 * @param ingredient which is related to different recipeIngredient objects.
	 * @return recipeIngredients in a list.
	 */
	public List<RecipeIngredient> getRecipeIngredient(Ingredient ingredient) {
		return recipeIngredientRepository.findByIngredient(ingredient);
	}

	/**
	 * Adds a recipeIngredient to the repository.
	 *
	 * @param recipeIngredient which will be stored via the repository.
	 */
	public RecipeIngredient addRecipeIngredient(RecipeIngredient recipeIngredient) {
		return recipeIngredientRepository.save(recipeIngredient);
	}

	/**
	 * Updates a recipeIngredient identified by a unique id.
	 *
	 * @param id which is used for identifying the recipeingredient to be updated.
	 * @param recipeIngredient which will replace the old recipeIngredient.
	 */
	public void updateRecipeIngredient(RecipeIngredientKey id, RecipeIngredient recipeIngredient) {
		recipeIngredientRepository.save(recipeIngredient); // if id already exists, Spring updates the id with the passed recipe instance 
	}

	/**
	 * Deletes a recipeIngredient identified by a unique id.
	 *
	 * @param id which is used for identifying the recipeingredient to be deleted.
	 */
	public void deleteRecipeIngredient(RecipeIngredientKey id) {
		recipeIngredientRepository.deleteById(id); // if id already exists, Spring updates the id with the passed recipe instance 
	}

	/**
	 * Deletes all recipeIngredients.
	 */
	public void deleteAll() {
		recipeIngredientRepository.deleteAll(); 
	}
}
