package com.hdmstuttgart.fluffybear.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Composite key consisting of recipe and ingredient id for the recipe ingredient relationship.
 */
@Embeddable
public class RecipeIngredientKey implements Serializable {

	@Column(name = "recipe_id")
	private long recipeId;
	
	@Column(name = "ingredient_id")
	private long ingredientId;
	
	public RecipeIngredientKey() {}
	
	public RecipeIngredientKey(long recipeId, long ingredientId) {
		this.recipeId = recipeId;
		this.ingredientId = ingredientId;
	}

	/**
	 * Compares current RecipeIngredient instance based on instance attributes with other given RecipeIngredient.
	 *
	 * @param obj  RecipeIngredient instance.
	 * @return  true if instances are equal, false if instances are not equal.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof RecipeIngredientKey))
			return false;
		if (obj == this)
			return true;
		return (this.getIngredientId() == ((RecipeIngredientKey) obj).getIngredientId())
				&& (this.getRecipeId() == ((RecipeIngredientKey) obj).getIngredientId());
	}

	/**
	 * Generate hashcode based on recipe and ingredient id.
	 *
	 * @return  hashcode for this composite key.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(this.getRecipeId(), this.getIngredientId());
	}

	/**
	 * Getter for recipeId member variable.
	 *
	 * @return  id for recipe in this relationship.
	 */
	public long getRecipeId() {
		return recipeId;
	}

	/**
	 * Setter for recipeId member variable.
	 *
	 * @param recipeId  recipe id.
	 */
	public void setRecipeId(long recipeId) {
		this.recipeId = recipeId;
	}

	/**
	 * Getter for ingredientId member variable.
	 *
	 * @return  id for ingredient in this relationship.
	 */
	public long getIngredientId() {
		return ingredientId;
	}

	/**
	 * Setter for ingredienId member variable.
	 *
	 * @param ingredienId  ingredien id.
	 */
	public void setIngredientId(long ingredienId) {
		this.ingredientId = ingredientId;
	}
}
