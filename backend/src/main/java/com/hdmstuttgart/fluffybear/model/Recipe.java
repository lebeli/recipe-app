package com.hdmstuttgart.fluffybear.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.util.*;

@Entity
@JsonIgnoreProperties({"id"})
public class Recipe {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String name;
	private String image;
	private int totalTime;
	private String category;
	private boolean vegetarian;
	private boolean vegan;

	@OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<RecipeIngredient> ingredients = new ArrayList<>();

	@ElementCollection(fetch=FetchType.EAGER)
	@Column(length=5000)
	private List<String> instructions = new ArrayList<>();

	public Recipe() {}
	
	public Recipe(String name) {
		this.name = name;
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

	// getters and setters
	
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
	public void setRecipeId(long id) {
		this.id = id;
	}

	public List<RecipeIngredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<RecipeIngredient> ingredients) {
		this.ingredients = ingredients;
	}

	public List<String> getInstructions() {
		return instructions;
	}

	public void setInstructions(List<String> instructions) {
		this.instructions = instructions;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}

	public boolean isVegetarian() {
		return vegetarian;
	}

	public void setVegetarian(boolean vegetarian) {
		this.vegetarian = vegetarian;
	}

	public boolean isVegan() {
		return vegan;
	}

	public void setVegan(boolean vegan) {
		this.vegan = vegan;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
