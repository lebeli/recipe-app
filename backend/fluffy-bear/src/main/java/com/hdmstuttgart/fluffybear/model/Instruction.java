package com.hdmstuttgart.fluffybear.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"id"})
public class Instruction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String instruction;

    @ManyToMany(mappedBy = "instructions")
    @JsonIgnore  // @JsonBackReference
    private List<Recipe> recipes = new ArrayList<>();

    public Instruction() {}

    public Instruction(String instruction) {
        this.instruction = instruction;
    }

    // utility functions

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    public void removeRecipe(Recipe recipe) {
        recipes.remove(recipe);
    }

    // getters and setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
}
