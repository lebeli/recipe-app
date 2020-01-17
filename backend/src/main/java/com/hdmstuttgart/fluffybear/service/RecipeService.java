package com.hdmstuttgart.fluffybear.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hdmstuttgart.fluffybear.model.Recipe;
import com.hdmstuttgart.fluffybear.repository.RecipeRepository;

/**
 * Spring boot service for recipes.
 */
@Service
public class RecipeService {

	/**
	 * Related Repository for recipes.
	 */
	@Autowired
	private RecipeRepository recipeRepository;

	/**
	 * Returns all recipes from the repository in a simple java list.
	 *
	 * @return the recipe list
	 */
	public List<Recipe> getAllRecipes() {
		List<Recipe> recipes = new ArrayList<Recipe>();
		recipeRepository.findAll()
		.forEach(recipe -> {
			recipes.add(recipe);
		});
		return recipes;
	}

	/**
	 * Applies a filter to all recipes and returns the list of the filtered recipes.
	 *
	 * @param minTime Minmal time of the recipe in minutes.
	 * @param maxTime Maximum time of the recipe in minutes.
	 * @param categories List of all possible categories for the recipe.
	 * @param vegetarian indicating if the recipe is vegetarian.
	 * @param vegan indicating if the recipe is vegan.
	 * @return the filtered list of recipes.
	 */
	public List<Recipe> getAllRecipesByFilter(int minTime, int maxTime, List<String> categories, boolean vegetarian, boolean vegan) {
		List<Recipe> recipes = new ArrayList<Recipe>();
		recipeRepository.findByJsonParameters(minTime, maxTime, categories, vegetarian, vegan)
		.forEach(recipe -> {
			recipes.add(recipe);
		});
		return recipes;
	}

	/**
	 * Applies a filter to all non vegan nor vegetarian recipes and returns the list of the filtered recipes.
	 *
	 * @param minTime Minmal time of the recipe in minutes.
	 * @param maxTime Maximum time of the recipe in minutes.
	 * @param categories List of all possible categories for the recipe.
	 * @return the filtered list of recipes.
	 */
	public List<Recipe> getAllRecipesByFilterNoneVeganVegetarian(int minTime, int maxTime, List<String> categories) {
		List<Recipe> recipes = new ArrayList<Recipe>();
		recipeRepository.findByJsonParametersNoneVeganVegetarian(minTime, maxTime, categories)
				.forEach(recipe -> {
					recipes.add(recipe);
				});
		return recipes;
	}

	/**
	 * Returns recipe object identified by a unique id.
	 *
	 * @param id which is used for identifying the recipe.
	 * @return the recipe
	 */
	public Recipe getRecipe(long id) {
		return recipeRepository.findById(id);
	}

	/**
	 * Returns recipe list identified by a name.
	 *
	 * @param name which is used for identifying the recipe.
	 * @return the list of recipes identified by name.
	 */
	public List<Recipe> getRecipe(String name) {
		return recipeRepository.findByName(name);
	}

	/**
	 * Saves a recipe via the repository.
	 *
	 * @param recipe which will be saved.
	 * @return  saved Recipe instance.
	 */
	public Recipe addRecipe(Recipe recipe) {
		return recipeRepository.save(recipe);
	}

	/**
	 * Updates a recipe identified by an id.
	 *
	 * @param id which identifies the recipe which will be updated.
	 * @param recipe which property values will update the old values.
	 */
	public void updateRecipe(long id, Recipe recipe) {
		recipeRepository.save(recipe); // if id already exists, Spring updates the id with the passed recipe instance 
	}

	/**
	 * Deletes a recipe identified by an id.
	 *
	 * @param id which identifies the recipe which will be deleted.
	 */
	public void deleteRecipe(long id) {
		recipeRepository.deleteById(id);
	}

	/**
	 * Deletes all recipes.
	 */
	public void deleteAll() {
		recipeRepository.deleteAll(); 
	}
}
