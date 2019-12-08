package com.hdmstuttgart.fluffybear.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "id"})
public class Ingredient {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String name;

	@OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<RecipeIngredient> recipes = new ArrayList<>();

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

	public void setId(long ingredientId) {
		this.id = ingredientId;
	}

	public List<RecipeIngredient> getRecipes() {
		return recipes;
	}

	public void setRecipes(List<RecipeIngredient> recipes) {
		this.recipes = recipes;
	}
}
