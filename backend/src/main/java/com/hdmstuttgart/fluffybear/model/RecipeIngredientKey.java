package com.hdmstuttgart.fluffybear.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RecipeIngredientKey implements Serializable {
	
	@Column(name = "recipe_id")
	private long recipeId;
	
	@Column(name = "ingredient_id")
	private String ingredientId;
	
	public RecipeIngredientKey() {}
	
	public RecipeIngredientKey(long recipeId, String ingredientId) {
		this.recipeId = recipeId;
		this.ingredientId = ingredientId;
	}
	
	// define unique equal properties for RecipeIngredientKey
	@Override
	public boolean equals(Object arg0) {
		if (arg0 == null)
			return false;
		if (!(arg0 instanceof RecipeIngredientKey))
			return false;
		if (arg0 == this)
			return true;
		return (this.getIngredientId() == ((RecipeIngredientKey) arg0).getIngredientId())
				&& (this.getRecipeId() == ((RecipeIngredientKey) arg0).getRecipeId());
	}

	// use recipe_id and ingredient_id for hash generation
	@Override
	public int hashCode() {
		return Objects.hash(this.getRecipeId(), this.getIngredientId());
	}

	public long getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(long recipeId) {
		this.recipeId = recipeId;
	}

	public String getIngredientId() {
		return ingredientId;
	}

	public void setIngredientId(long ingredienId) {
		this.ingredientId = ingredientId;
	}
}
