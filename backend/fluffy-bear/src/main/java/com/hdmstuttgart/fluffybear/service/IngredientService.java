package com.hdmstuttgart.fluffybear.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdmstuttgart.fluffybear.model.Ingredient;
import com.hdmstuttgart.fluffybear.repository.IngredientRepository;

@Service
public class IngredientService {

	@Autowired
	private IngredientRepository ingredientRepository;

	public List<Ingredient> getAllIngredients() {
		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		ingredientRepository.findAll().forEach(ingredient -> {
			ingredients.add(ingredient);
		});
		return ingredients;
	}
	
	public Ingredient getIngredient(long id) {
		return ingredientRepository.findById(id);
	}
	
	public List<Ingredient> getIngredient(String name) {
		return ingredientRepository.findByName(name);
	}
	
	public void addIngredient(Ingredient ingredient) {
		ingredientRepository.save(ingredient);
	}
	
	public void updateIngredient(long id, Ingredient ingredient) {
		ingredientRepository.save(ingredient); // if id already exists, Spring updates the id with the passed recipe instance 
	}
	
	public void deleteIngredient(long id) {
		ingredientRepository.deleteById(id); // if id already exists, Spring updates the id with the passed recipe instance 
	}
	
	public void deleteAll() {
		ingredientRepository.deleteAll(); 
	}
}
