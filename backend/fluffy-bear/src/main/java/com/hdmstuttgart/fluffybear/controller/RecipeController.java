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
import com.hdmstuttgart.fluffybear.model.Instruction;
import com.hdmstuttgart.fluffybear.service.InstructionService;
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
import javax.persistence.criteria.CriteriaBuilder;

@RestController
public class RecipeController {
	
	@Autowired
	private RecipeService recipeService;

	@Autowired
	private InstructionService instructionService;
	
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
		List<Instruction> instructions = recipe.getInstructions();
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

	@RequestMapping("/sample")
	public Recipe getSample() {
		Recipe recipe = new Recipe("Spaghetti", "Boil and stuff", 2);
		Ingredient noodles = new Ingredient("Noodles");
		Ingredient sauce = new Ingredient("Sauce");
		Instruction noodleInst = new Instruction("Boil noodles.");
		Instruction sauceInst = new Instruction("Heat sauce.");

		recipe.addIngredient(noodles);
		recipe.addIngredient(sauce);
		recipe.addInstruction(noodleInst);
		recipe.addInstruction(sauceInst);

		return recipe;
	}

	@RequestMapping("/recipes/{id}")
    public Recipe getRecipe(@PathVariable("id") Long id) {
		return recipeService.getRecipe(id);
    }
}
