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
	
	public List<Recipe> getAllRecipes(Long id) {
		List<Recipe> recipes = new ArrayList<Recipe>();
		recipeRepository.findAll()
		.forEach(topic -> {
			recipes.add(topic);
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
	
	public void updateRecipe(Long id, Recipe recipe) {
		recipeRepository.save(recipe); // if id already exists, Spring updates the id with the passed recipe instance 
	}
	
	public void deleteRecipe(Long id) {
		recipeRepository.deleteById(id); // if id already exists, Spring updates the id with the passed recipe instance 
	}
}
