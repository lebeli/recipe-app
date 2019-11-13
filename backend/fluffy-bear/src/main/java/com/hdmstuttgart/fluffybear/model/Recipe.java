package com.hdmstuttgart.fluffybear.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Recipe {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	private String instruction;
	private int yield;
	
	@OneToMany(mappedBy = "recipe")
    private Set<RecipeIngredient> ingredientAmount;
	
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

	public Set<RecipeIngredient> getIngredientAmount() {
		return ingredientAmount;
	}

	public void setIngredientAmount(Set<RecipeIngredient> ingredientAmount) {
		this.ingredientAmount = ingredientAmount;
	}
}
