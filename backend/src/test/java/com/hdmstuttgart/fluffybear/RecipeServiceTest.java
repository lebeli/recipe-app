package com.hdmstuttgart.fluffybear;

import com.hdmstuttgart.fluffybear.model.Recipe;
import com.hdmstuttgart.fluffybear.repository.RecipeRepository;
import com.hdmstuttgart.fluffybear.service.RecipeService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class RecipeServiceTest {
    @InjectMocks
    RecipeService recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Test
    public void getAllRecipes() {
        List<Recipe> allRecipes = new ArrayList<>();
        Recipe recipe1 = new Recipe("Spaghetti");
        Recipe recipe2 = new Recipe("Soup");
        Recipe recipe3 = new Recipe("Pancakes");
        allRecipes.add(recipe1);
        allRecipes.add(recipe2);
        allRecipes.add(recipe3);

        when(recipeRepository.findAll()).thenReturn(allRecipes);

        assertEquals(3, recipeService.getAllRecipes().size());
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    public void getRecipesByIdTest() {
        Recipe recipe1 = new Recipe("Spaghetti");
        Recipe recipe2 = new Recipe("Soup");
        recipe1.setId(1);
        recipe2.setId(2);

        when(recipeRepository.findById(1)).thenReturn(recipe1);
        when(recipeRepository.findById(2)).thenReturn(recipe2);

        assertEquals(1, recipeService.getRecipe(1).getId());
        assertEquals(2, recipeService.getRecipe(2).getId());
        assertEquals("Spaghetti", recipeService.getRecipe(1).getName());
        assertEquals("Soup", recipeService.getRecipe(2).getName());
        verify(recipeRepository, times(2)).findById(1);
        verify(recipeRepository, times(2)).findById(2);
    }

    @Test
    public void getRecipesByNameTest() {
        Recipe recipe1 = new Recipe("Spaghetti");
        Recipe recipe2 = new Recipe("Soup");
        Recipe recipe3 = new Recipe("Soup");
        recipe1.setId(1);
        recipe2.setId(2);
        recipe3.setId(3);

        when(recipeRepository.findByName("Spaghetti")).thenReturn(new ArrayList<Recipe>(Arrays.asList(recipe1)));
        when(recipeRepository.findByName("Soup")).thenReturn(new ArrayList<Recipe>(Arrays.asList(recipe2, recipe3)));

        List<Recipe> spaghettiQuery = recipeService.getRecipe("Spaghetti");
        List<Recipe> soupQuery = recipeService.getRecipe("Soup");

        assertEquals(1, spaghettiQuery.size());
        assertEquals(2, soupQuery.size());
        assertEquals("Spaghetti", spaghettiQuery.get(0).getName());
        assertEquals("Soup", soupQuery.get(0).getName());
        assertEquals("Soup", soupQuery.get(1).getName());
        verify(recipeRepository, times(1)).findByName("Spaghetti");
        verify(recipeRepository, times(1)).findByName("Soup");
    }

    @Test
    public void addAndUpdateRecipeTest() {
        Recipe recipe = new Recipe("Spaghetti");

        recipeService.addRecipe(recipe);
        recipeService.updateRecipe(1, recipe);

        verify(recipeRepository, times(2)).save(recipe);
    }

    @Test
    public void deleteRecipeTest() {
        Recipe recipe = new Recipe("Spaghetti");
        recipeService.deleteRecipe(1);
        verify(recipeRepository, times(1)).deleteById(1L);
    }

    @Test
    public void getAllRecipesByFilterTest() {
        List<Recipe> allRecipes = new ArrayList<>();
        Recipe recipe1 = new Recipe("Spaghetti");
        Recipe recipe2 = new Recipe("Soup");
        Recipe recipe3 = new Recipe("Pancakes");
        // spaghetti
        recipe1.setCategory("Dinner");
        recipe1.setTotalTime(30);
        recipe1.setVegan(false);
        recipe1.setVegetarian(true);
        // soup
        recipe2.setCategory("Lunch");
        recipe2.setTotalTime(45);
        recipe2.setVegan(true);
        recipe2.setVegetarian(true);
        // pancakes
        recipe3.setCategory("Breakfast");
        recipe3.setTotalTime(10);
        recipe3.setVegan(false);
        recipe3.setVegetarian(true);
        allRecipes.add(recipe1);
        allRecipes.add(recipe2);
        allRecipes.add(recipe3);

        List<String> categoriesFilter = new ArrayList<String>(Arrays.asList("Dinner", "Lunch"));

        when(recipeRepository.findByJsonParameters(anyInt(), anyInt(), anyList(), anyBoolean(), eq(true)))
                .thenReturn(new ArrayList<Recipe>(
                        allRecipes
                                .stream()
                                .filter(recipe -> recipe.isVegan())
                                .collect(Collectors.toList())
                ));
        when(recipeRepository.findByJsonParameters(anyInt(), anyInt(), eq(categoriesFilter), anyBoolean(), anyBoolean()))
                .thenReturn(new ArrayList<Recipe>(
                        allRecipes
                                .stream()
                                .filter(recipe -> (categoriesFilter).contains(recipe.getCategory()))
                                .collect(Collectors.toList())
                ));

        List<Recipe> filteredRecipesByVegan = recipeService.getAllRecipesByFilter(0, 180, new ArrayList<String>(Arrays.asList("Dinner", "Lunch", "Breakfast")), true, true);
        List<Recipe> filteredRecipesByCategory = recipeService.getAllRecipesByFilter(0, 180, new ArrayList<String>(Arrays.asList("Dinner", "Lunch")), true, true);
        assertEquals(1, filteredRecipesByVegan.size());
        assertEquals("Soup", filteredRecipesByVegan.get(0).getName());
        assertEquals(2, filteredRecipesByCategory.size());
        assertEquals("Spaghetti", filteredRecipesByCategory.get(0).getName());
        assertEquals("Soup", filteredRecipesByCategory.get(1).getName());
        verify(recipeRepository, times(2)).findByJsonParameters(anyInt(), anyInt(), anyList(), anyBoolean(), anyBoolean());
    }
}
