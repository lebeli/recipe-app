package com.hdmstuttgart.fluffybear.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hdmstuttgart.fluffybear.model.Recipe;
import com.hdmstuttgart.fluffybear.repository.RecipeRepository;

@Service
public class RecipeService {
	
	@Autowired
	private RecipeRepository recipeRepository;
	
	public List<Recipe> getAllRecipes() {
		List<Recipe> recipes = new ArrayList<Recipe>();
		recipeRepository.findAll()
		.forEach(recipe -> {
			recipes.add(recipe);
		});
		return recipes;
	}

	public List<Recipe> getAllRecipesByFilter(int minTime, int maxTime, List<String> categories, boolean vegetarian, boolean vegan) {
		List<Recipe> recipes = new ArrayList<Recipe>();
		recipeRepository.findByJsonParameters(minTime, maxTime, categories, vegetarian, vegan)
		.forEach(recipe -> {
			recipes.add(recipe);
		});
		return recipes;
	}

	public Recipe getRecipe(long id) {
		return recipeRepository.findById(id);
	}
	
	public List<Recipe> getRecipe(String name) {
		return recipeRepository.findByName(name);
	}
	
	public void addRecipe(Recipe recipe) {
		recipeRepository.save(recipe);
	}

	public void updateRecipe(long id, Recipe recipe) {
		recipeRepository.save(recipe); // if id already exists, Spring updates the id with the passed recipe instance 
	}
	
	public void deleteRecipe(long id) {
		recipeRepository.deleteById(id);
	}
	
	public void deleteAll() {
		recipeRepository.deleteAll(); 
	}
}
