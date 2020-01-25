package com.hdmstuttgart.fluffybear.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Class for modelling an ingredient by uid, name and recipes it is contained in.
 */
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "id"})
public class Ingredient {
	@Id
	@JsonProperty("name")
	private String id;

	/**
	 * List of RecipeIngredient instances, containing all recipes this ingredient is needed for.
	 */
	@OneToMany(mappedBy = "ingredient", orphanRemoval = true)
	@JsonIgnore
	private List<RecipeIngredient> recipes = new ArrayList<>();

	public Ingredient() {}
	
	public Ingredient(String name) {
		this.id = name;
	}

	/**
	 * Instanciates new RecipeIngredient with given Recipe instance beeing assigned to the recipe member variable
	 * and the self reference to the ingredient member variable. The RecipeIngredient instance is beeing added to the recipes
	 * list.
	 *
	 * @param recipe  recipe instance to be added to the new RecipeIngredient instance.
	 */
	public void addRecipe(Recipe recipe) {
		RecipeIngredient recipeIngredient= new RecipeIngredient(recipe, this);
		recipes.add(recipeIngredient);
		recipe.getIngredients().add(recipeIngredient);
	}

	/**
	 * Iterates over all RecipeIngredient instances and removes every the each instance with matching Ingredient instance
	 * referenced by the ingredient member variable.
	 *
	 * @param recipe  ingredient instance that defines which RecipeIngredient instance is going to be removed.
	 */
	public void removeRecipe(Recipe recipe) {
		for (Iterator<RecipeIngredient> iterator = recipes.iterator();
			 iterator.hasNext(); ) {
			RecipeIngredient recipeIngredient = iterator.next();

			if (recipeIngredient.getIngredient().equals(this) &&
					recipeIngredient.getRecipe().equals(recipe)) {
				iterator.remove();
				recipeIngredient.setRecipe(null);
				recipeIngredient.setIngredient(null);
			}
		}
	}

	/**
	 * Getter for id member variable.
	 *
	 * @return  recipe id.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Setter for id member variable.
	 *
	 * @param ingredientId  id to be set for recipe instance.
	 */
	public void setId(String ingredientId) {
		this.id = ingredientId;
	}

	/**
	 * Getter for recipes list with RecipeIngredient instances.
	 *
	 * @return  RecipeIngredient list.
	 */
	public List<RecipeIngredient> getRecipes() {
		return recipes;
	}

	/**
	 * Setter for recipes member variable.
	 *
	 * @param recipes  list with RecipeIngredient instances.
	 */
	public void setRecipes(List<RecipeIngredient> recipes) {
		this.recipes = recipes;
	}
}
