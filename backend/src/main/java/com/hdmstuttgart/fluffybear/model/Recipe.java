package com.hdmstuttgart.fluffybear.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.util.*;

/**
 * Class for modelling an recipe by uid, name, image, cooking time, category (breakfast, lunch, dinner) wether it is
 * vegetarian/vegan, needed ingredients and cooking instructions.
 */
@Entity
@JsonIgnoreProperties({"id"})
public class Recipe {
	/**
	 * Recipe id.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	/**
	 * Recipe name.
	 */
	private String name;
	/**
	 * Image for recipe.
	 */
	private String image;
	/**
	 * Estimated preparation and cooking time.
	 */
	private int totalTime;
	/**
	 * Recipe category (breakfast, lunch or dinner).
	 */
	private String category;
	/**
	 * Wether or not recipe is vegetarian.
	 */
	private boolean vegetarian;
	/**
	 * Wether or not recipe is vegan.
	 */
	private boolean vegan;

	/**
	 * List of RecipeIngredient instances, containing all ingredients needed for this recipe.
	 */
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

	/**
	 * Instanciates new RecipeIngredient with given Ingredient instance beeing assigned to the ingredient member variable
	 * and the self reference to the recipe member variable. The RecipeIngredient instance is beeing added to the instructions
	 * list.
	 *
	 * @param ingredient  ingredient instance to be added to the new RecipeIngredient instance.
	 */
	public void addIngredient(Ingredient ingredient) {
	    RecipeIngredient recipeIngredient= new RecipeIngredient(this, ingredient);
	    ingredients.add(recipeIngredient);
	}

	/**
	 * Iterates over all RecipeIngredient instances and removes every the each instance with matching Ingredient instance
	 * referenced by the ingredient member variable.
	 *
	 * @param ingredient  ingredient instance that defines which RecipeIngredient instance is going to be removed.
	 */
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

	/**
	 * Adds an instruction string to the intructions list.
	 *
	 * @param instruction  instruction string to be added to the list.
	 */
	public void addInstruction(String instruction) {
		this.instructions.add(instruction);
	}

	// getters and setters

	/**
	 * Getter for name member variable.
	 *
	 * @return  recipe name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for name member variable.
	 *
	 * @param name  string to be set as name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter for id member variable.
	 *
	 * @return  recipe id.
	 */
	public long getId() {
		return id;
	}

	/**
	 * Setter for id member variable.
	 *
	 * @param id  id to be set for recipe instance.
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Getter for ingredients list with RecipeIngredient instances.
	 *
	 * @return  RecipeIngredient list.
	 */
	public List<RecipeIngredient> getIngredients() {
		return ingredients;
	}

	/**
	 * Setter for ingredients member variable.
	 *
	 * @param ingredients  list with RecipeIngredient instances.
	 */
	public void setIngredients(List<RecipeIngredient> ingredients) {
		this.ingredients = ingredients;
	}

	/**
	 * Getter for instructions list.
	 *
	 * @return  list with instruction strings.
	 */
	public List<String> getInstructions() {
		return instructions;
	}

	/**
	 * Setter for instructions member variable.
	 *
	 * @param instructions  list with instruction strings.
	 */
	public void setInstructions(List<String> instructions) {
		this.instructions = instructions;
	}

	/**
	 * Getter for image member variable.
	 *
	 * @return  url string for each image resource.
	 */
	public String getImage() {
		return image;
	}

	/**
	 * Setter for image member variable.
	 *
	 * @param image  url string for a given image.
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * Getter for totalTime member variable.
	 *
	 * @return  estimated time in minutes for this recipe.
	 */
	public int getTotalTime() {
		return totalTime;
	}

	/**
	 * Setter for totalTime member variable.
	 *
	 * @param totalTime  estimated time in minutes for this recipe.
	 */
	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}

	/**
	 * Getter for vegetarian member variable.
	 *
	 * @return  true if recipe is vegetarian, false if recipe is not vegetarian.
	 */
	public boolean isVegetarian() {
		return vegetarian;
	}

	/**
	 * Setter for vegetarian member variable.
	 *
	 * @param vegetarian  true or false, depending if recipe is vegatarian or not.
	 */
	public void setVegetarian(boolean vegetarian) {
		this.vegetarian = vegetarian;
	}

	/**
	 * Getter for vegan member variable.
	 *
	 * @return  true if recipe is vegan, false if recipe is not vegan.
	 */
	public boolean isVegan() {
		return vegan;
	}

	/**
	 * Setter for vegan member variable.
	 *
	 * @param vegan  true or false, depending if recipe is vegan or not.
	 */
	public void setVegan(boolean vegan) {
		this.vegan = vegan;
	}

	/**
	 * Getter for category member variable.
	 *
	 * @return  recipe category.
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Setter for category member variable.
	 *
	 * @param category  category string.
	 */
	public void setCategory(String category) {
		this.category = category;
	}
}
