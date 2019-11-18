package com.hdmstuttgart.fluffybear.controller;

import java.util.HashSet;
import java.util.Set;

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

@RestController
public class RecipeController {
	
	@Autowired
	private RecipeService recipeService;
	
	@Autowired
	private IngredientService ingredientService;
	
	@Autowired
	private RecipeIngredientService recipeIngredientService;
	
	@RequestMapping("/recipes")
    public Recipe getRecipes() {
		recipeService.deleteAll();
		ingredientService.deleteAll();
		recipeIngredientService.deleteAll();
		
		Ingredient noodles = new Ingredient("Noodles");
		Ingredient mincedMeat = new Ingredient("Minced Meat");
		Ingredient tomatoes = new Ingredient("Tomatoes");
		Recipe recipe = new Recipe("Spaghetti Bolognese", "Boil pasta. Add minced meat and tomatoes.", 2);
		RecipeIngredient recipeIngredient1 = new RecipeIngredient(recipe, noodles, 300);
		RecipeIngredient recipeIngredient2 = new RecipeIngredient(recipe, mincedMeat, 150);
		RecipeIngredient recipeIngredient3 = new RecipeIngredient(recipe, tomatoes, 200);
		
		Set<RecipeIngredient> recipeIngredients = new HashSet<RecipeIngredient>();
		recipeIngredients.add(recipeIngredient1);
		recipeIngredients.add(recipeIngredient2);
		recipeIngredients.add(recipeIngredient3);
		
		recipe.setRecipeIngredients(recipeIngredients);
		noodles.setRecipeIngredients(recipeIngredients);
		mincedMeat.setRecipeIngredients(recipeIngredients);
		tomatoes.setRecipeIngredients(recipeIngredients);
		
		recipeService.addRecipe(recipe);
		ingredientService.addIngredient(noodles);
		ingredientService.addIngredient(mincedMeat);
		ingredientService.addIngredient(tomatoes);
		recipeIngredientService.addRecipeIngredient(recipeIngredient1);
		recipeIngredientService.addRecipeIngredient(recipeIngredient2);
		recipeIngredientService.addRecipeIngredient(recipeIngredient3);
		
		return recipe;
    }
	
	@RequestMapping("/recipes/{id}")
    public Recipe getRecipe(@PathVariable("id") Long id) {
		return recipeService.getRecipe(id);
    }
	
	@PostMapping(value = "/recipes/add", consumes = "application/json")
	public void addRecipe(@RequestBody Recipe recipe) {
		recipeService.deleteAll();
		ingredientService.deleteAll();
		recipeIngredientService.deleteAll();
		
		Recipe postRecipe = recipe;
		Set<Ingredient> postIngredients = new HashSet<>();
		Set<RecipeIngredient> recipeIngredients = postRecipe.getRecipeIngredients();
		
		recipeIngredients.forEach(recipeIngredient -> {
			postIngredients.add(recipeIngredient.getIngredient());
//			recipeIngredientService.addRecipeIngredient(recipeIngredient);
		});
		
		postIngredients.forEach(ingredient -> {
			ingredientService.addIngredient(ingredient);
		});
		
		System.out.println("Success");
		recipeService.addRecipe(recipe);
	}
}
