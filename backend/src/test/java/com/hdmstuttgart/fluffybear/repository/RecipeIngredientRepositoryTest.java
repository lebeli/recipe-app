package com.hdmstuttgart.fluffybear.repository;

import com.hdmstuttgart.fluffybear.TestUtilities;
import com.hdmstuttgart.fluffybear.model.Ingredient;
import com.hdmstuttgart.fluffybear.model.Recipe;
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

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase
@Transactional
public class RecipeIngredientRepositoryTest {
    @Autowired
    private RecipeIngredientRepository recipeIngredientRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    private List<Ingredient> queryResult;

    @Before
    public void setUp() {
        queryResult = new ArrayList<>();
    }

    @Test
    public void repositoryInitTest() {
        assertEquals(294, recipeIngredientRepository.count());
    }

    @Test
    public void saveIngredientTest() {
        long initialSize = recipeIngredientRepository.count();
        Recipe recipe = TestUtilities.buildRecipe("Recipe", Arrays.asList("Ingredient"));
        Ingredient ingredient = recipe.getIngredients().get(0).getIngredient();
        recipeRepository.save(recipe);
        ingredientRepository.save(ingredient);  // RecipeIngredient implicitly saved
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

