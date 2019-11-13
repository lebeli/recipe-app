package com.hdmstuttgart.fluffybear.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class Recipe {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String name;
	private String instruction;
	private int yield;
	
	@ManyToMany
	@JoinTable(
			  name = "contained_ingredience", 
			  joinColumns = @JoinColumn(name = "recipe_id"), 
			  inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
	private Set<String> ingredients;

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
}
