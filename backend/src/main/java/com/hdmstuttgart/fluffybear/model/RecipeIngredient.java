package com.hdmstuttgart.fluffybear.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import javax.persistence.*;


@Entity
public class RecipeIngredient {
	@EmbeddedId
	@JsonIgnore
    private RecipeIngredientKey id = new RecipeIngredientKey();
	
	@ManyToOne(fetch = FetchType.LAZY)
    @MapsId("recipeId")
	@JoinColumn(name = "recipe_id", referencedColumnName = "id")
	@JsonIgnore
	private Recipe recipe;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @MapsId("ingredientId")
	@JoinColumn(name = "ingredient_id", referencedColumnName = "id")
	@JsonUnwrapped
	private Ingredient ingredient;

	private int amount;
	
	public RecipeIngredient() {}

	public RecipeIngredient(Recipe recipe, Ingredient ingredient) {
		this.recipe = recipe;
		this.ingredient = ingredient;
	}
	
	public RecipeIngredient(Recipe recipe, Ingredient ingredient, int amount) {
		this.recipe = recipe;
		this.ingredient = ingredient;
		this.amount = amount;
	}

	public RecipeIngredientKey getId() {
		return id;
	}

	public void setId(RecipeIngredientKey id) {
		this.id = id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
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
