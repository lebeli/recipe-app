package com.hdmstuttgart.fluffybear.controller;

import java.util.ArrayList;
import java.util.List;

import com.hdmstuttgart.fluffybear.storage.FileSystemStorageService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hdmstuttgart.fluffybear.model.Ingredient;
import com.hdmstuttgart.fluffybear.model.Recipe;
import com.hdmstuttgart.fluffybear.model.RecipeIngredient;

import com.hdmstuttgart.fluffybear.service.IngredientService;
import com.hdmstuttgart.fluffybear.service.RecipeIngredientService;
import com.hdmstuttgart.fluffybear.service.RecipeService;


/**
 * Controller Class for handling recipe requests and saving/filtering/returning recipes as well as related ingredients.
 */
@RestController
public class RecipeController {
	private final static Logger logger = Logger.getLogger(FileSystemStorageService.class);

	@Autowired
	private RecipeService recipeService;

	@Autowired
	private IngredientService ingredientService;
	
	@Autowired
	private RecipeIngredientService recipeIngredientService;

	/**
	 * Handler for /recipes resource GET requests. Reads request parameters from GET request header and returns fitting recipes in
	 * the http response body.
	 *
	 * @param breakfast  boolean if breakfast recipes should be considered.
	 * @param lunch  boolean if lunch recipes should be considered.
	 * @param dinner  boolean if dinner recipes should be considered.
	 * @param vegetarian  boolean if vegetarian recipes should be considered.
	 * @param vegan  boolean if vegan recipes should be considered.
	 * @param longTime  boolean if recipes with over 60 min preparation time should be considered.
	 * @param shortTime  boolean if recipes with under 30 min preparation time should be considered.
	 * @return recipes fitting the given query parameters.
	 */
	@RequestMapping(value = "/recipes/all", consumes = {"application/json"})
	public List<Recipe> getAllRecipesByFilter(
			@RequestHeader(value="breakfast") boolean breakfast,
			@RequestHeader(value="lunch") boolean lunch,
			@RequestHeader(value="dinner") boolean dinner,
			@RequestHeader(value="vegetarian") boolean vegetarian,
			@RequestHeader(value="vegan") boolean vegan,
			@RequestHeader(value="longTime") boolean longTime,
			@RequestHeader(value="shortTime") boolean shortTime
	) {
		return recipeService.getAllRecipesByFilter(breakfast, lunch, dinner, vegetarian, vegan, longTime, shortTime);
	}

	@RequestMapping(value = "/recipes", consumes = {"application/json"})
	public Recipe getRandomRecipeByFilter(
			@RequestHeader(value="breakfast") boolean breakfast,
			@RequestHeader(value="lunch") boolean lunch,
			@RequestHeader(value="dinner") boolean dinner,
			@RequestHeader(value="vegetarian") boolean vegetarian,
			@RequestHeader(value="vegan") boolean vegan,
			@RequestHeader(value="longTime") boolean longTime,
			@RequestHeader(value="shortTime") boolean shortTime
	) {
		return recipeService.getOneRecipeByFilter(breakfast, lunch, dinner, vegetarian, vegan, longTime, shortTime);
	}

	/**
	 * Handler for /recipes/[id] resource GET requests returning a single requested recipe.
	 *
	 * @param id  id for requested recipe.
	 * @return  recipe with fitting id.
	 */
	@RequestMapping("/recipes/{id}")
	public Recipe getRecipe(@PathVariable("id") Long id) {
		return recipeService.getRecipe(id);
	}

	/**
	 * Handler for /recipes/add resource. Reads JSON from POST request body and adds a recipe, ingredient and fitting
	 * composite key to the database.
	 *
	 * @param recipe  unmarshaled Recipe instance from request body JSON.
	 */
	@PostMapping("/recipes/add")
	public void saveRecipe(@RequestBody Recipe recipe) {
		// get lists with Ingredient and RecipeIngredient instances
		List<Ingredient> ingredients = new ArrayList<>();
		List<RecipeIngredient> recipeIngredients = recipe.getIngredients();
		recipeIngredients.forEach(recipeIngredient -> {
			ingredients.add(recipeIngredient.getIngredient());
		});
		
		// add Recipe instance to RecipeIngredient instances (maps relationship)
		recipe.getIngredients().forEach(recipeIngredient -> {
			recipeIngredient.setRecipe(recipe);
		});
		recipeService.addRecipe(recipe);
		logger.info("New recipe added.");
	}
}
