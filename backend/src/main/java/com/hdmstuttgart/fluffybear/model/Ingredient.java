package com.hdmstuttgart.fluffybear.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for modelling an ingredient by uid, name and recipes it is contained in.
 */
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

	/**
	 * Getter for name member variable.
	 *
	 * @return  ingredient name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for name member variable.
	 *
	 * @param name  name to be set for ingredient.
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
	 * Getter for recipes list with RecipeIngredient instances.
	 *
	 * @return  RecipeIngredient list.
	 */
	public List<RecipeIngredient> getRecipes() {
		return recipes;
	}

	/**
	 * Setter for recipes member variable.
	 *
	 * @param recipes  list with RecipeIngredient instances.
	 */
	public void setRecipes(List<RecipeIngredient> recipes) {
		this.recipes = recipes;
	}
}
