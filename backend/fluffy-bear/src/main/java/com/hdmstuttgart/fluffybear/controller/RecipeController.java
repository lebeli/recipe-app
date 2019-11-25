package com.hdmstuttgart.fluffybear.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hdmstuttgart.fluffybear.AppConfig;
import com.hdmstuttgart.fluffybear.DemoDataController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hdmstuttgart.fluffybear.model.Ingredient;
import com.hdmstuttgart.fluffybear.model.Recipe;
import com.hdmstuttgart.fluffybear.model.RecipeIngredient;

import com.hdmstuttgart.fluffybear.service.IngredientService;
import com.hdmstuttgart.fluffybear.service.RecipeIngredientService;
import com.hdmstuttgart.fluffybear.service.RecipeService;

import javax.annotation.PostConstruct;

@RestController
public class RecipeController {
	
	@Autowired
	private RecipeService recipeService;
	
	@Autowired
	private IngredientService ingredientService;
	
	@Autowired
	private RecipeIngredientService recipeIngredientService;
	
	@RequestMapping("/recipes")
    public List<Recipe> getRecipes() {
		return recipeService.getAllRecipes();
	}

	@PostConstruct
	public void init() {
		System.out.println(AppConfig.getInstance().isDemoMode());
		if (AppConfig.getInstance().isDemoMode()) {
			DemoDataController.getInstance().getDemoRecipes().forEach(this::saveRecipe);
		}
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
	
	@RequestMapping("/recipes/{id}")
    public Recipe getRecipe(@PathVariable("id") Long id) {
		return recipeService.getRecipe(id);
    }
}
