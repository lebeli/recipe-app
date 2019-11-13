package com.hdmstuttgart.fluffybear.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RecipeIngredientKey implements Serializable {	

	@Column(name = "recipe_id", nullable = false)
    Long recipeId;
 
    @Column(name = "ingredient_id", nullable = false)
    Long ingredientId;
}
