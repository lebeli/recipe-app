package com.hdmstuttgart.fluffybear.repository;

import com.hdmstuttgart.fluffybear.TestUtilities;
import com.hdmstuttgart.fluffybear.model.Ingredient;
import com.hdmstuttgart.fluffybear.model.Recipe;
import com.hdmstuttgart.fluffybear.model.RecipeIngredient;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


public class RecipeRepositoryTest extends RepositoryTest {
    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private RecipeIngredientRepository recipeIngredientRepository;

    private Recipe testRecipe;

    private final int MAX_TIME = Integer.MAX_VALUE;
    private final List<String> CATEGORIES = Arrays.asList("breakfast");

    @Before
    public void saveTestRecipe() {
        this.testRecipe = TestUtilities.buildRecipe("TestRecipe", Arrays.asList("TestIngredient"), CATEGORIES.get(0), 45);
        ingredientRepository.save(testRecipe.getIngredients().get(0).getIngredient());
        recipeIngredientRepository.save(testRecipe.getIngredients().get(0));
        recipeRepository.save(testRecipe);
    }

    @Test
    public void repositoryInitTest() {
        recipeRepository.findAll().forEach(recipe -> {
            queryResult.add(recipe);
        });
        assertEquals(32, queryResult.size());
    }

    @Test
    public void saveRecipeTest() {
        Recipe recipeTest = new Recipe("Test");
        recipeRepository.save(recipeTest);
        recipeRepository.findByName("Test").forEach(recipe -> {
            queryResult.add(recipe);
        });
        assertEquals("Test", queryResult.get(0).getName());
    }

    @Test
    public void saveDuplicateTest() {
        recipeRepository.findByName("Mickriges Müsli").forEach(recipe -> {
            queryResult.add(recipe);
        });
        recipeRepository.save(queryResult.get(0));
        queryResult.clear();
        recipeRepository.findAll().forEach(recipe -> {
            queryResult.add(recipe);
        });
        assertEquals(32, queryResult.size());
    }

    @Test
    public void deleteRecipeTest() {
        recipeRepository.deleteById(1L);
        recipeRepository.findAll().forEach(recipe -> {
            queryResult.add(recipe);
        });
        assertEquals(31, queryResult.size());
    }

    @Test
    public void findAllByJsonParametersTest() {
        recipeRepository.findAllByJsonParameters(45, new ArrayList<>(Arrays.asList("breakfast")), false, false).forEach(recipe -> {
            queryResult.add(recipe);
        });
        assertEquals("Frivole Frühstückseier mit Pfiff", queryResult.get(0).getName());
    }

    @Test
    public void findAllByJsonParametersIngredientsTest() {
        recipeRepository.findAllByJsonParametersIngredients(MAX_TIME, CATEGORIES, true, false, Arrays.asList("TestIngredient")).forEach(recipe -> queryResult.add(recipe));
        assertEquals("TestIngredient", queryResult.get(0).getIngredients().get(0).getIngredient().getId());
    }

    @Test
    public void findAllByJsonParametersNoneVeganVegetarianTest() {
        boolean testRecipeInQuery = false;
        recipeRepository.findAllByJsonParametersNoneVeganVegetarian(MAX_TIME, CATEGORIES).forEach(recipe -> queryResult.add(recipe));
        for(int i = 0; i < queryResult.size(); i++) {
            if(queryResult.get(i).getName() == "TestRecipe") testRecipeInQuery = true;
        }
        assertTrue(testRecipeInQuery);
    }

    @Test
    public void findAllByJsonParametersNoneVeganVegetarianIngredientsTest() {
        recipeRepository.findAllByJsonParametersNoneVeganVegetarianIngredients(MAX_TIME, CATEGORIES, Arrays.asList("TestIngredient")).forEach(recipe -> queryResult.add(recipe));
        assertEquals("TestIngredient", queryResult.get(0).getIngredients().get(0).getIngredient().getId());
    }

    @Test
    public void findOneByJsonParametersTest() {
        boolean differentResults = false;
        Recipe recipeResult = recipeRepository.findOneByJsonParameters(MAX_TIME, Arrays.asList("breakfast", "lunch", "dinner"), true, false);
        for(int i = 0; i < 15; i++) {
            Recipe currentRecipeResult = recipeRepository.findOneByJsonParameters(MAX_TIME, Arrays.asList("breakfast", "lunch", "dinner"), true, false);
            if(!recipeResult.equals(currentRecipeResult)) {
                differentResults = true;
            }
        }
        assertTrue(differentResults);
    }

    @Test
    public void findOneByJsonParametersIngredientsTest() {
        boolean differentResults = false;
        boolean containedTestRecipe = false;
        Recipe recipeResult = recipeRepository.findOneByJsonParametersIngredients(MAX_TIME, CATEGORIES, true, false, Arrays.asList("TestIngredient", "Salz"));
        for(int i = 0; i < 15; i++) {
            Recipe currentRecipeResult = recipeRepository.findOneByJsonParametersIngredients(MAX_TIME, CATEGORIES, true, false, Arrays.asList("TestIngredient", "Kartoffelmehl"));
            if(!recipeResult.equals(currentRecipeResult)) {
                differentResults = true;
            }
            if(currentRecipeResult.getName() == "TestRecipe") {
                containedTestRecipe = true;
            }
        }
        assertTrue((differentResults && containedTestRecipe));
    }

    @Test
    public void findOneByJsonParametersNoneVeganVegetarianTest() {
        boolean differentResults = false,
                containedTestRecipe = false,
                containedBreakfast = false,
                containedVegetarian = false,
                containedVegan = false;
        Recipe recipeResult = recipeRepository.findOneByJsonParametersNoneVeganVegetarian(MAX_TIME, CATEGORIES);
        for(int i = 0; i < 15; i++) {
            Recipe currentRecipeResult = recipeRepository.findOneByJsonParametersNoneVeganVegetarian(MAX_TIME, CATEGORIES);
            if(!recipeResult.equals(currentRecipeResult)) differentResults = true;
            if(currentRecipeResult.getName() == "TestRecipe") containedTestRecipe = true;
            containedBreakfast = (currentRecipeResult.getCategory().equals("breakfast"));
            if(currentRecipeResult.isVegetarian()) containedVegetarian = true;
            if(currentRecipeResult.isVegan()) containedVegan = true;
        }
        assertTrue((differentResults && containedTestRecipe && containedBreakfast && containedVegetarian && containedVegan));
    }

    @Test
    public void findOneByJsonParametersNoneVeganVegetarianIngredientsTest() {
        boolean identicaleResults = true;
        Recipe recipeResult = recipeRepository.findOneByJsonParametersNoneVeganVegetarianIngredients(MAX_TIME, CATEGORIES, Arrays.asList("TestIngredient"));
        for(int i = 0; i < 15; i++) {
            Recipe currentRecipeResult = recipeRepository.findOneByJsonParametersNoneVeganVegetarianIngredients(MAX_TIME, CATEGORIES, Arrays.asList("TestIngredient"));
            if(!recipeResult.equals(currentRecipeResult)) identicaleResults = false;
        }
        assertTrue(identicaleResults);
    }
}
