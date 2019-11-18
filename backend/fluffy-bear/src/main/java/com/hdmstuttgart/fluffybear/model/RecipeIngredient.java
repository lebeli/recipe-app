package com.hdmstuttgart.fluffybear.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class RecipeIngredient {	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
//    @MapsId("recipe_id")
    @JoinColumn(name = "recipe_id")
	@JsonBackReference(value = "recipe")
    private Recipe recipe;  // back reference is ignored during JSON serialization (due to recursion)
 
    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    @JsonBackReference(value = "ingredient")
    private Ingredient ingredient;  // managed reference will be serialized
 
    @JoinColumn(name = "ingredient_name")
    String ingredientName;
    
    int ingredientAmount;
    
    public RecipeIngredient() {}
    
    public RecipeIngredient(Recipe recipe, Ingredient ingredient, int ingredientAmount) {
    	this.recipe = recipe;
    	this.ingredient = ingredient;
    	this.ingredientAmount = ingredientAmount;
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
