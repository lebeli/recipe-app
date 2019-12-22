package com.hdmstuttgart.fluffybear.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.hdmstuttgart.fluffybear.model.Category;
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
	
	@RequestMapping("/recipes")
    public List<Recipe> getRecipes(
			@RequestParam(defaultValue = "true") Boolean breakfast,
			@RequestParam(defaultValue = "true") Boolean lunch,
			@RequestParam(defaultValue = "true") Boolean dinner,
			@RequestParam(defaultValue = "false") Boolean vegetarian,
			@RequestParam(defaultValue = "false") Boolean vegan,
			@RequestParam(required = false) Boolean longTime,
			@RequestParam(required = false) Boolean shortTime
	) {
		return recipeService.getAllRecipes().stream().filter(recipe -> {
			boolean accepted = true;

			if (!breakfast && recipe.getCategory() == Category.BREAKFAST) {
					return false;
			}
			if (!lunch && recipe.getCategory() == Category.LUNCH) {
					return false;
			}
			if (!dinner && recipe.getCategory() == Category.DINNER) {
					return false;
			}
			if (vegetarian && !recipe.isVegetarian()) {
				return false;
			}
			if (vegan && !recipe.isVegan()) {
				return false;
			}

			if (longTime != null && longTime) {
				accepted = recipe.getTotalTime() >= 60;
			}

			if (shortTime != null && shortTime) {
				accepted = recipe.getTotalTime() <= 10;
			}

			if (shortTime != null && shortTime && longTime != null && longTime) { // Impossible
				accepted = false;
			}

			return accepted;
		}).collect(Collectors.toList());
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
