package com.hdmstuttgart.fluffybear.model;

import com.hdmstuttgart.fluffybear.model.Ingredient;
import com.hdmstuttgart.fluffybear.model.Recipe;
import com.hdmstuttgart.fluffybear.model.RecipeIngredient;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RecipeIngredientTest {
    private Recipe spaghetti;
    private Ingredient sauce;
    private RecipeIngredient spaghettiSauce;

    @Before
    public void setUpEntities() {
        spaghetti = new Recipe("Spaghetti");
        sauce = new Ingredient("Sauce");
        spaghettiSauce = new RecipeIngredient(spaghetti, sauce, "200 g");
    }

    @Test
    public void ingredientRecipeGetAmount() {
        assertEquals("200 g", spaghettiSauce.getTypeAmount());
    }

    @Test
    public void ingredientRecipeGetRecipe() {
        assertEquals(spaghetti, spaghettiSauce.getRecipe());
    }

    @Test
    public void ingredientRecipeGetIngredient() {
        assertEquals(sauce, spaghettiSauce.getIngredient());
    }

    @Test
    public void ingredientRecipeSAmount() {
        spaghettiSauce.setTypeAmount("300 g");
        assertEquals("300 g", spaghettiSauce.getTypeAmount());
    }

    @Test
    public void ingredientRecipeSetRecipe() {
        Recipe tortellini = new Recipe("Tortellini");
        spaghettiSauce.setRecipe(tortellini);
        assertEquals(tortellini, spaghettiSauce.getRecipe());
    }

    @Test
    public void ingredientRecipeSetIngredient() {
        Ingredient cheese = new Ingredient("Cheese");
        spaghettiSauce.setIngredient(cheese);
        assertEquals(cheese, spaghettiSauce.getIngredient());
    }
}
