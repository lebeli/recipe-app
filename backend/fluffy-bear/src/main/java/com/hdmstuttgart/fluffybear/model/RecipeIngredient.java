package com.hdmstuttgart.fluffybear.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class RecipeIngredient {
	
	@EmbeddedId
	private RecipeIngredientKey id;
	
	@ManyToOne
    @MapsId("recipe_id")
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;
 
    @ManyToOne
    @MapsId("ingredient_id")
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;
 
    int ingredientAmount;
    
    public RecipeIngredient(Recipe recipe, Ingredient ingredient) {
    	this.recipe = recipe;
    	this.ingredient = ingredient;
    }

	public int getIngredientAmount() {
		return ingredientAmount;
	}

	public void setIngredientAmount(int ingredientAmount) {
		this.ingredientAmount = ingredientAmount;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}
}
