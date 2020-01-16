package com.hdmstuttgart.fluffybear;

import com.hdmstuttgart.fluffybear.model.Ingredient;
import com.hdmstuttgart.fluffybear.model.Recipe;
import com.hdmstuttgart.fluffybear.model.RecipeIngredient;

import java.util.ArrayList;
import java.util.List;

public class TestUtilities {
    // TODO: implement utility functions
    public static Recipe exampleRecipe() {
        Recipe recipe = new Recipe("Spaghetti");
        Ingredient noodles = new Ingredient("Noodles");
        Ingredient sauce = new Ingredient("Sauce");
        RecipeIngredient spaghettiNoodles = new RecipeIngredient();
        RecipeIngredient spaghettiSauce = new RecipeIngredient();

        List<RecipeIngredient> spaghettiIngredients = new ArrayList<>();
        List<RecipeIngredient> noodleRecipes = new ArrayList<>();
        List<RecipeIngredient> sauceRecipes = new ArrayList<>();

        spaghettiNoodles.setRecipe(recipe);
        spaghettiNoodles.setIngredient(noodles);
        spaghettiSauce.setRecipe(recipe);
        spaghettiSauce.setIngredient(sauce);

        spaghettiIngredients.add(spaghettiNoodles);
        spaghettiIngredients.add(spaghettiSauce);
        noodleRecipes.add(spaghettiNoodles);
        sauceRecipes.add(spaghettiSauce);

        recipe.setIngredients(spaghettiIngredients);
        noodles.setRecipes(noodleRecipes);
        sauce.setRecipes(sauceRecipes);

        return recipe;
    }
}
