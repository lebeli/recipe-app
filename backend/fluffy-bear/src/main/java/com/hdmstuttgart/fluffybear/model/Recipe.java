package com.hdmstuttgart.fluffybear.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.*;

@Entity
@JsonIgnoreProperties({"id"})
public class Recipe {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String name;
	private String description;
	private int yield;

	@OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<RecipeIngredient> ingredients = new ArrayList<>();

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "recipe_instruction",
			joinColumns = @JoinColumn(name = "recipe_id"),
			inverseJoinColumns = @JoinColumn(name = "instruction_id"))
	@JsonProperty // without annotation instructions is not beeing serialized (alternative: @JsonProperty and @JsonIgnore)
	private List<Instruction> instructions = new ArrayList<>();

	public Recipe() {}
	
	public Recipe(String name, String description, int yield) {
		this.name = name;
		this.description = description;
		this.yield = yield;
	}
	
	// utility functions
	
	public void addIngredient(Ingredient ingredient) {
	    RecipeIngredient recipeIngredient= new RecipeIngredient(this, ingredient);
	    ingredients.add(recipeIngredient);
	}

	public void removeIngredient(Ingredient ingredient) {
	    for (Iterator<RecipeIngredient> iterator = ingredients.iterator(); 
	         iterator.hasNext(); ) {
	    	RecipeIngredient recipeIngredient = iterator.next();

	        if (recipeIngredient.getRecipe().equals(this) &&
	        		recipeIngredient.getIngredient().equals(ingredient)) {
	            iterator.remove();
	            recipeIngredient.setRecipe(null);
	            recipeIngredient.setIngredient(null);
	        }
	    }
	}

	public void addInstruction(Instruction instruction) {
		instruction.addRecipe(this);
		instructions.add(instruction);
	}

	public void removeInstruction(Instruction instruction) {
		instructions.remove(instruction);
		instruction.removeRecipe(this);
	}

	// getters and setters
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getYield() {
		return yield;
	}

	public void setYield(int yield) {
		this.yield = yield;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	public void setRecipeId(long id) {
		this.id = id;
	}

	public List<RecipeIngredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<RecipeIngredient> ingredients) {
		this.ingredients = ingredients;
	}

	public List<Instruction> getInstructions() {
		return instructions;
	}

	public void setInstructions(List<Instruction> instructions) {
		this.instructions = instructions;
	}
}
