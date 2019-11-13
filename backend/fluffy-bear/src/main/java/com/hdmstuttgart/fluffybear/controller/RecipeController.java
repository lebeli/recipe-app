package com.hdmstuttgart.fluffybear.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hdmstuttgart.fluffybear.model.Ingredient;
import com.hdmstuttgart.fluffybear.model.Recipe;
import com.hdmstuttgart.fluffybear.model.RecipeIngredient;
import com.hdmstuttgart.fluffybear.service.RecipeService;

@RestController
public class RecipeController {
	
	@Autowired
	private RecipeService recipeService;
	
	@RequestMapping("/recipes")
    public Recipe getRecipes() {
		// TODO: implement
		Ingredient ingredient = new Ingredient("Nudel");
		Recipe recipe = new Recipe("Sad Pasta", "Boil pasta.", 1);
		RecipeIngredient recipeIngredient = new RecipeIngredient(recipe, ingredient, 1);	
		return recipe;
    }
	
	@RequestMapping("/recipes/{id}")
    public Recipe getRecipe(@PathVariable("id") Long id) {
		return recipeService.getRecipe(id);
    }
}
