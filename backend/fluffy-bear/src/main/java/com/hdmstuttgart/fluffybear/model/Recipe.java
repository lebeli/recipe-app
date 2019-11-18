package com.hdmstuttgart.fluffybear.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Set;

@Entity
public class Recipe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
	private String instruction;
	private int yield;
	
	@OneToMany(mappedBy = "recipe")
	@JsonManagedReference(value = "recipe")
    private Set<RecipeIngredient> recipeIngredients;  // managed reference will be serialized
	
	public Recipe() {}
	
	public Recipe(String name, String instruction, int yield) {
		this.name = name;
		this.instruction = instruction;
		this.yield = yield;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	public int getYield() {
		return yield;
	}

	public void setYield(int yield) {
		this.yield = yield;
	}

	public Set<RecipeIngredient> getRecipeIngredients() {
		return recipeIngredients;
	}

	public void setRecipeIngredients(Set<RecipeIngredient> recipeIngredient) {
		this.recipeIngredients = recipeIngredient;
	}
}
