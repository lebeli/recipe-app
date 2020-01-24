package com.hdmstuttgart.fluffybear.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdmstuttgart.fluffybear.model.Ingredient;
import com.hdmstuttgart.fluffybear.repository.IngredientRepository;

import javax.persistence.EntityNotFoundException;

/**
 * Spring boot service for ingredients.
 */
@Service
public class IngredientService {

	/**
	 * Related Repository for ingredients.
	 */
	@Autowired
	private IngredientRepository ingredientRepository;

	/**
	 * Returns all ingredients from the repository in a simple java list.
	 *
	 * @return the ingredient list
	 */
	public List<Ingredient> getAllIngredients() {
		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		ingredientRepository.findAll().forEach(ingredient -> {
			ingredients.add(ingredient);
		});
		return ingredients;
	}

	/**
	 * Get ingredients by a given name.
	 *
	 * @param id  name which is related to an ingredient.
	 * @return ingredients in a list.
	 */
	public Ingredient getIngredient(String id) {
		return ingredientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
	}

	/**
	 * Adds an ingredient to the repository.
	 *
	 * @param ingredient which needs to be stored.
	 */
	public Ingredient addIngredient(Ingredient ingredient) {
	    return ingredientRepository.save(ingredient);
	}

	/**
	 * Updates specific ingredient identified by the id.
	 *
	 * @param id which identifies the unique ingredient.
	 * @param ingredient which replaces the old ingredient.
	 */
	public void updateIngredient(long id, Ingredient ingredient) {
		ingredientRepository.save(ingredient); // if id already exists, Spring updates the id with the passed recipe instance 
	}

	/**
	 * Removes ingredient identified by the id.
	 *
	 * @param id which refers to ingredient which well be deleted.
	 */
	public void deleteIngredient(String id) {
		ingredientRepository.deleteById(id); // if id already exists, Spring updates the id with the passed recipe instance
	}

	/**
	 * Deletes all ingredients.
	 */
	public void deleteAll() {
		ingredientRepository.deleteAll(); 
	}
}
