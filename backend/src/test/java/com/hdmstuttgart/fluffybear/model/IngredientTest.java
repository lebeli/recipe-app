package com.hdmstuttgart.fluffybear.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

// do not use SpringBootTest for unit testing (no need for spring boot application context)
@RunWith(JUnit4.class)
public class IngredientTest {

    private Recipe recipeSpaghetti;
    private Ingredient sauce;
    private RecipeIngredient spaghettiSauce;

    @Before
    public void setUpEntities() {
        recipeSpaghetti = new Recipe("Spaghetti");
        sauce = new Ingredient("Sauce");
        spaghettiSauce = new RecipeIngredient();
        spaghettiSauce.setRecipe(recipeSpaghetti);
        spaghettiSauce.setIngredient(sauce);
        List<RecipeIngredient> sauceRecipes = new ArrayList<>();
        sauceRecipes.add(spaghettiSauce);
        sauce.setRecipes(sauceRecipes);
    }

    @Test
    public void ingredientGetName() {
        assertEquals("Sauce", sauce.getId());
    }

    @Test
    public void ingredientSetName() {
        sauce.setId("Sauce Hollandaise");
        assertEquals("Sauce Hollandaise", sauce.getId());
    }

    @Test
    public void ingredientGetRecipes() {
        List<RecipeIngredient> recipes = new ArrayList<>();
        recipes.add(spaghettiSauce);
        assertTrue(recipes.equals(sauce.getRecipes()));
    }

    @Test
    public void ingredientSetRecipes() {
        List<RecipeIngredient> recipes = new ArrayList<>();
        recipes.add(spaghettiSauce);
        assertTrue(recipes.equals(sauce.getRecipes()));
    }
}
