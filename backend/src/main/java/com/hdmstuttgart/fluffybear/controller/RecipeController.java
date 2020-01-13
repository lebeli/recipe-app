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

@RestController
public class RecipeController {
	
	@Autowired
	private RecipeService recipeService;

	@Autowired
	private IngredientService ingredientService;
	
	@Autowired
	private RecipeIngredientService recipeIngredientService;
	
	@RequestMapping(value = "/recipes", consumes = {"application/json"})
    public List<Recipe> getRecipes(@RequestBody Map<String, Boolean> filter) {
		List<String> categories = new ArrayList<String>();
		if(filter.get("breakfast")) { categories.add("breakfast"); }
		if(filter.get("lunch")) { categories.add("lunch"); }
		if(filter.get("dinner")) { categories.add("dinner"); }
		int minTime = 30;
		int maxTime = 60;
		if(filter.get("shortTime")) { minTime = 0; }
		if(filter.get("shortTime")) { maxTime = 180; }

		return recipeService.getAllRecipesByFilter(minTime, maxTime, categories, filter.get("vegetarian"), filter.get("vegan"));
	}

	@RequestMapping("/recipes/{id}")
	public Recipe getRecipe(@PathVariable("id") Long id) {
		return recipeService.getRecipe(id);
	}
	
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

	@RequestMapping("/ingredients")
	public List<Ingredient> getIngredients() {
		return ingredientService.getAllIngredients();
	}
}
