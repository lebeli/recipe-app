package com.hdmstuttgart.fluffybear.repository;

import com.hdmstuttgart.fluffybear.TestUtilities;
import com.hdmstuttgart.fluffybear.model.Ingredient;
import com.hdmstuttgart.fluffybear.model.Recipe;
import com.hdmstuttgart.fluffybear.model.RecipeIngredient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RecipeIngredientRepositoryTest extends RepositoryTest {
    @Autowired
    private RecipeIngredientRepository recipeIngredientRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Test
    public void repositoryInitTest() {
        assertEquals(272, recipeIngredientRepository.count());
    }

    @Test
    public void saveRecipeIngredientTest() {
        long initialSize = recipeIngredientRepository.count();
        Recipe recipe = TestUtilities.buildRecipe("Recipe", Arrays.asList("Ingredient"), "dinner", 30);
        Ingredient ingredient = recipe.getIngredients().get(0).getIngredient();
        RecipeIngredient recipeIngredient = new RecipeIngredient(recipe, ingredient, "2 Spoons");
        recipeRepository.save(recipe);
        ingredientRepository.save(ingredient);
        recipeIngredientRepository.save(recipeIngredient);
        assertEquals(initialSize+1, recipeIngredientRepository.count());
        assertEquals("Recipe", recipeIngredientRepository.findByRecipe(recipe).get(0).getRecipe().getName());
    }

    @Test
    public void saveDuplicateTest() {
        long initialSize = recipeIngredientRepository.count();
        Ingredient ingredient = ingredientRepository.findById("Eier").get();
        recipeIngredientRepository.save(recipeIngredientRepository.findByIngredient(ingredient).get(0));
        assertEquals(initialSize, recipeIngredientRepository.count());
    }

    @Test
    public void deleteRecipeIngredientTest() {
        long initialSize = recipeIngredientRepository.count();
        Ingredient ingredient = ingredientRepository.findById("Eier").get();
        recipeIngredientRepository.delete(recipeIngredientRepository.findByIngredient(ingredient).get(0));
        assertEquals(initialSize-1, recipeIngredientRepository.count());
    }
}

