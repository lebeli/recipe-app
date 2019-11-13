package com.hdmstuttgart.fluffybear.model;

import javax.persistence.EmbeddedId;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

public class RecipeIngredient {
	
	@EmbeddedId
	RecipeIngredientKey id;
	
	@ManyToOne
    @MapsId("recipe_id")
    @JoinColumn(name = "recipe_id")
    Recipe recipe;
 
    @ManyToOne
    @MapsId("ingredient_id")
    @JoinColumn(name = "ingredient_id")
    Ingredient ingredient;
 
    int ingredientAmount;

	public int getIngredientAmount() {
		return ingredientAmount;
	}

	public void setIngredientAmount(int ingredientAmount) {
		this.ingredientAmount = ingredientAmount;
	}
}
