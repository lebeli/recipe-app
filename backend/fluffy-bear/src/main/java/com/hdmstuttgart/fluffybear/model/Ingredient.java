package com.hdmstuttgart.fluffybear.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Set;

@Entity
public class Ingredient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
	
	@OneToMany(mappedBy = "ingredient")
	@JsonManagedReference(value = "ingredient")
    Set<RecipeIngredient> recipeIngredients;  // back reference is ignored during JSON serialization
	
	public Ingredient() {}
	
	public Ingredient(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Set<RecipeIngredient> getRecipeIngredients() {
		return recipeIngredients;
	}

	public void setRecipeIngredients(Set<RecipeIngredient> recipeIngredients) {
		this.recipeIngredients = recipeIngredients;
	}
}
