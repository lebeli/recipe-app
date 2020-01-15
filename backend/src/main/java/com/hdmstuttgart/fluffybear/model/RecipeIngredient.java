package com.hdmstuttgart.fluffybear.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import javax.persistence.*;

/**
 * Class for modeling the bidirectional relationship between a recipe and its containing ingredients.
 */
@Entity
public class RecipeIngredient {
	/**
	 * Composite key for recipe and ingredient id.
	 */
	@EmbeddedId
	@JsonIgnore
    private RecipeIngredientKey id = new RecipeIngredientKey();

	/**
	 * Recipe instance of this relationship.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
    @MapsId("recipeId")
	@JoinColumn(name = "recipe_id", referencedColumnName = "id")
	@JsonIgnore
	private Recipe recipe;

	/**
	 * Ingredient instance of this relationship.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
    @MapsId("ingredientId")
	@JoinColumn(name = "ingredient_id", referencedColumnName = "id")
	@JsonUnwrapped
	private Ingredient ingredient;

	/**
	 * Ingredient amount for a realated recipe.
	 */
	private String typeAmount;
	
	public RecipeIngredient() {}

	public RecipeIngredient(Recipe recipe, Ingredient ingredient) {
		this.recipe = recipe;
		this.ingredient = ingredient;
	}
	
	public RecipeIngredient(Recipe recipe, Ingredient ingredient, String typeAmount) {
		this.recipe = recipe;
		this.ingredient = ingredient;
		this.typeAmount = typeAmount;
	}

	/**
	 * Getter for id member variable.
	 *
	 * @return  composite key with recipe and ingredient id.
	 */
	public RecipeIngredientKey getId() {
		return id;
	}

	/**
	 * Setter for id member variable.
	 *
	 * @param id  composite key with recipe and ingredient id.
	 */
	public void setId(RecipeIngredientKey id) {
		this.id = id;
	}

	/**
	 * Getter for recipe member variable.
	 *
	 * @return  recipe instance.
	 */
	public Recipe getRecipe() {
		return recipe;
	}

	/**
	 * Setter for recipe member variable.
	 *
	 * @param recipe  recipe related to ingredient.
	 */
	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	/**
	 * Getter for ingredient member variable.
	 *
	 * @return  ingredient instance.
	 */
	public Ingredient getIngredient() {
		return ingredient;
	}

	/**
	 * Setter for ingredient member variable.
	 *
	 * @param ingredient  ingredient related to recipe.
	 */
	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	/**
	 * Getter for ingredient amount in this relation.
	 *
	 * @return  amount in g for ingredient.
	 */
	public String getTypeAmount() {
		return typeAmount;
	}

	/**
	 * Setter for ingredient amount in this relation.
	 *
	 * @param typeAmount   amount in g for ingredient.
	 */
	public void setTypeAmount(String typeAmount) {
		this.typeAmount = typeAmount;
	}
}
