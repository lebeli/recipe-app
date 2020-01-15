package com.hdmstuttgart.fluffybear.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	 * @return  recipes fitting the given query parameters.
	 */
	@RequestMapping(value = "/recipes", consumes = {"application/json"})
    public List<Recipe> getRecipes(
    		@RequestHeader(value="breakfast") boolean breakfast,
			@RequestHeader(value="lunch") boolean lunch,
			@RequestHeader(value="dinner") boolean dinner,
			@RequestHeader(value="vegetarian") boolean vegetarian,
			@RequestHeader(value="vegan") boolean vegan,
			@RequestHeader(value="longTime") boolean longTime,
			@RequestHeader(value="shortTime") boolean shortTime
			) {
		List<String> categories = new ArrayList<String>();
		if(breakfast) { categories.add("breakfast"); }
		if(lunch) { categories.add("lunch"); }
		if(dinner) { categories.add("dinner"); }
		int minTime = 30;
		int maxTime = 60;
		if(shortTime) { minTime = 0; }
		if(shortTime) { maxTime = 180; }
		if(!vegan && !vegetarian) {
			return recipeService.getAllRecipesByFilterNoneVeganVegetarian(minTime, maxTime, categories);
		}
		return recipeService.getAllRecipesByFilter(minTime, maxTime, categories, vegetarian, vegan);
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
	 * composite key to the respective repositories.
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

		// persist entities
		recipeService.addRecipe(recipe);
		ingredients.forEach(ingredient -> {
			ingredientService.addIngredient(ingredient);
		});
		recipeIngredients.forEach(recipeIngredient -> {
			recipeIngredientService.addRecipeIngredient(recipeIngredient); //
		});
	}

	/**
	 * Handler für /ingredients resource GET requests.
	 *
	 * @return  list of all ingredients in respond body.
	 */
	@RequestMapping("/ingredients")
	public List<Ingredient> getIngredients() {
		return ingredientService.getAllIngredients();
	}
}
