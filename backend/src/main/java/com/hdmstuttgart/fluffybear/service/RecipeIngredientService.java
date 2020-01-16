package com.hdmstuttgart.fluffybear.service;

import java.util.ArrayList;
import java.util.List;

import com.hdmstuttgart.fluffybear.model.RecipeIngredientKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdmstuttgart.fluffybear.model.Ingredient;
import com.hdmstuttgart.fluffybear.model.Recipe;
import com.hdmstuttgart.fluffybear.model.RecipeIngredient;
import com.hdmstuttgart.fluffybear.repository.RecipeIngredientRepository;

@Service
public class RecipeIngredientService {
	
	@Autowired
	RecipeIngredientRepository recipeIngredientRepository;
	
	public List<RecipeIngredient> getAllRecipeIngredients() {
		List<RecipeIngredient> recipeIngredients = new ArrayList<RecipeIngredient>();
		recipeIngredientRepository.findAll().forEach(recipeIngredient -> {
			recipeIngredients.add(recipeIngredient);
		});
		return recipeIngredients;
	}
	
//	public RecipeIngredient getRecipeIngredient(RecipeIngredientKey id) {
//		return recipeIngredientRepository.findByKey(id);
//	}
	
	public List<RecipeIngredient> getRecipeIngredient(Recipe recipe) {
		return recipeIngredientRepository.findByRecipe(recipe);
	}
	
	public List<RecipeIngredient> getRecipeIngredient(Ingredient ingredient) {
		return recipeIngredientRepository.findByIngredient(ingredient);
	}
	
	public RecipeIngredient addRecipeIngredient(RecipeIngredient recipeIngredient) {
		return recipeIngredientRepository.save(recipeIngredient);
	}
	
	public void updateRecipeIngredient(RecipeIngredientKey id, RecipeIngredient recipeIngredient) {
		recipeIngredientRepository.save(recipeIngredient); // if id already exists, Spring updates the id with the passed recipe instance 
	}
	
	public void deleteRecipeIngredient(RecipeIngredientKey id) {
		recipeIngredientRepository.deleteById(id); // if id already exists, Spring updates the id with the passed recipe instance 
	}
	
	public void deleteAll() {
		recipeIngredientRepository.deleteAll(); 
	}
}
