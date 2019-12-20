package com.hdmstuttgart.fluffybear;

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
        spaghettiSauce = new RecipeIngredient(spaghetti, sauce, 125);
    }

    @Test
    public void ingredientRecipeGetAmount() {
        assertEquals(125, spaghettiSauce.getAmount());
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
        spaghettiSauce.setAmount(150);
        assertEquals(150, spaghettiSauce.getAmount());
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
