package com.hdmstuttgart.fluffybear.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hdmstuttgart.fluffybear.model.Recipe;
import com.hdmstuttgart.fluffybear.repository.RecipeRepository;

import javax.persistence.EntityNotFoundException;

/**
 * Spring boot service for recipes.
 */
@Service
public class RecipeService {

	final private int DEFAULT_TIME = 45;
	final private int LONG_TIME = Integer.MAX_VALUE;
	final private int SHORT_TIME = 20;

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
	 * @param breakfast  indicating if recipe is for lunch.
	 * @param lunch  maximum time of the recipe in minutes.
	 * @param dinner  list of all possible categories for the recipe.
	 * @param vegetarian  indicating if the recipe is vegetarian.
	 * @param vegan  indicating if the recipe is vegan.
	 * @return  the filtered list of recipes.
	 */
	public List<Recipe> getAllRecipesByFilter(boolean breakfast, boolean lunch, boolean dinner, boolean vegetarian, boolean vegan, boolean longTime, boolean shortTime, String[] ingredients) {
		List<Recipe> recipes = new ArrayList<Recipe>();
		List<String> categories = new ArrayList<>();
		if(breakfast) { categories.add("breakfast"); }
		if(lunch) { categories.add("lunch"); }
		if(dinner) { categories.add("dinner"); }
		int maxTime = DEFAULT_TIME;
		if(shortTime) { maxTime = SHORT_TIME; }
		if(longTime) { maxTime = LONG_TIME; }
		if(!vegan && !vegetarian) {
			if(ingredients.length == 0) {
				recipeRepository.findAllByJsonParametersNoneVeganVegetarian(maxTime, categories).forEach(recipe -> {
					recipes.add(recipe);
				});
			} else {
				recipeRepository.findAllByJsonParametersNoneVeganVegetarianIngredients(maxTime, categories, Arrays.asList(ingredients)).forEach(recipe -> {
					recipes.add(recipe);
				});
			}
		} else {
			if(ingredients.length == 0) {
				recipeRepository.findAllByJsonParameters(maxTime, categories, vegetarian, vegan).forEach(recipe -> {
					recipes.add(recipe);
				});
			} else {
				recipeRepository.findAllByJsonParametersIngredients(maxTime, categories, vegetarian, vegan, Arrays.asList(ingredients)).forEach(recipe -> {
					recipes.add(recipe);
				});
			}
		}
		return recipes;
	}

	/**
	 * Applies a filter to all recipes and returns a single random recipe of the resulting subset.
	 *
	 * @param breakfast  indicating if recipe is for lunch.
	 * @param lunch  maximum time of the recipe in minutes.
	 * @param dinner  list of all possible categories for the recipe.
	 * @param vegetarian  indicating if the recipe is vegetarian.
	 * @param vegan  indicating if the recipe is vegan.
	 * @return  the filtered list of recipes.
	 */
	public Recipe getOneRecipeByFilter(boolean breakfast, boolean lunch, boolean dinner, boolean vegetarian, boolean vegan, boolean longTime, boolean shortTime, String[] ingredients) {
		List<String> categories = new ArrayList<String>();
		if(breakfast) { categories.add("breakfast"); }
		if(lunch) { categories.add("lunch"); }
		if(dinner) { categories.add("dinner"); }
		if(breakfast == false && lunch == false && dinner == false) {
			categories.add("breakfast");
			categories.add("lunch");
			categories.add("dinner");
		}
		int maxTime = LONG_TIME;
		if(shortTime) { maxTime = SHORT_TIME; }
		if(!vegan && !vegetarian) {
			if(ingredients.length == 0) {
				return recipeRepository.findOneByJsonParametersNoneVeganVegetarian(maxTime, categories);
			} else {
				return recipeRepository.findOneByJsonParametersNoneVeganVegetarianIngredients(maxTime, categories, Arrays.asList(ingredients));
			}
		} else {
			if(vegan) {vegetarian = true;};
			int i = ingredients.length;
			if(ingredients.length == 0) {
				return recipeRepository.findOneByJsonParameters(maxTime, categories, vegetarian, vegan);
			} else {
				return recipeRepository.findOneByJsonParametersIngredients(maxTime, categories, vegetarian, vegan, Arrays.asList(ingredients));
			}
		}
	}

	/**
	 * Returns recipe object identified by a unique id.
	 *
	 * @param id which is used for identifying the recipe.
	 * @return the recipe
	 */
	public Recipe getRecipe(Long id) {
		return recipeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id.toString()));
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
