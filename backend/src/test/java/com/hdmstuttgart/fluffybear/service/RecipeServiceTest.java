package com.hdmstuttgart.fluffybear.service;

import com.hdmstuttgart.fluffybear.model.Recipe;
import com.hdmstuttgart.fluffybear.repository.RecipeRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Before;
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
    private RecipeService recipeService;

    @Mock
    private RecipeRepository recipeRepository;

    private List<Recipe> allRecipes = new ArrayList<>();
    private Recipe spaghetti;
    private Recipe soup;
    private Recipe pancakes;

    @Before
    public void setUpEntities() {
        spaghetti = new Recipe("Spaghetti");
        soup = new Recipe("Soup");
        pancakes = new Recipe("Pancakes");
        spaghetti.setId(1);
        soup.setId(2);
        pancakes.setId(3);
        allRecipes.add(spaghetti);
        allRecipes.add(soup);
        allRecipes.add(pancakes);
    }

    @Test
    public void getAllRecipesTest() {
        when(recipeRepository.findAll()).thenReturn(allRecipes);
        assertEquals(3, recipeService.getAllRecipes().size());
        verify(recipeRepository, times(1)).findAll();
    }

    @Test void getAllRecipesFilterTest() {
        // TODO
    }

    @Test void getAllRecipesFilterNoneVeganVegetarianTest() {
        // TODO
    }

    @Test void getAllRecipesFilterIngredientsTest() {
        // TODO
    }

    @Test void getAllRecipesFilterNoneVeganVegetarianIngredientsTest() {
        // TODO
    }

    @Test void getOneRecipesFilterTest() {
        // TODO
    }

    @Test void getOneRecipesFilterNoneVeganVegetarianTest() {
        // TODO
    }

    @Test void getOneRecipesFilterIngredientsTest() {
        // TODO
    }

    @Test void getOneRecipesFilterNoneVeganVegetarianIngredientsTest() {
        // TODO
    }

    @Test
    public void getRecipesByIdTest() {
        when(recipeRepository.findById(1L)).thenReturn(Optional.of(spaghetti));
        when(recipeRepository.findById(2L)).thenReturn(Optional.of(soup));

        assertEquals(1, (recipeService.getRecipe(1L)).getId());
        assertEquals(2, recipeService.getRecipe(2L).getId());
        assertEquals("Spaghetti", recipeService.getRecipe(1L).getName());
        assertEquals("Soup", recipeService.getRecipe(2L).getName());
        verify(recipeRepository, times(2)).findById(1L);
        verify(recipeRepository, times(2)).findById(2L);
    }

    @Test
    public void getRecipesByNameTest() {
        Recipe soup2 = new Recipe("Soup");
        soup2.setId(4);

        when(recipeRepository.findByName("Spaghetti")).thenReturn(new ArrayList<Recipe>(Arrays.asList(spaghetti)));
        when(recipeRepository.findByName("Soup")).thenReturn(new ArrayList<Recipe>(Arrays.asList(soup, soup2)));

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
        recipeService.addRecipe(spaghetti);
        recipeService.updateRecipe(1, spaghetti);
        verify(recipeRepository, times(2)).save(spaghetti);
    }

    @Test
    public void deleteRecipeTest() {
        recipeService.deleteRecipe(1);
        verify(recipeRepository, times(1)).deleteById(1L);
    }

    @Test
    public void getAllRecipesByFilterTest() {
        // spaghetti
        spaghetti.setCategory("dinner");
        spaghetti.setTotalTime(30);
        spaghetti.setVegan(false);
        spaghetti.setVegetarian(true);
        // soup
        soup.setCategory("lunch");
        soup.setTotalTime(45);
        soup.setVegan(true);
        soup.setVegetarian(true);
        // pancakes
        pancakes.setCategory("Breakfast");
        pancakes.setTotalTime(10);
        pancakes.setVegan(false);
        pancakes.setVegetarian(false);

        List<String> categoriesFilter = new ArrayList<String>(Arrays.asList("lunch", "dinner"));

        when(recipeRepository.findAllByJsonParameters(anyInt(), anyList(), anyBoolean(), eq(true)))
                .thenReturn(new ArrayList<Recipe>(
                        allRecipes
                                .stream()
                                .filter(recipe -> recipe.isVegan())
                                .collect(Collectors.toList())
                ));
        when(recipeRepository.findAllByJsonParameters(anyInt(), eq(categoriesFilter), anyBoolean(), anyBoolean()))
                .thenReturn(new ArrayList<Recipe>(
                        allRecipes
                                .stream()
                                .filter(recipe -> (categoriesFilter).contains(recipe.getCategory()))
                                .collect(Collectors.toList())
                ));
        when(recipeRepository.findAllByJsonParametersNoneVeganVegetarian(anyInt(), eq(categoriesFilter)))
                .thenReturn(new ArrayList<Recipe>(
                        allRecipes
                                .stream()
                                .filter(recipe -> !recipe.isVegan() && !recipe.isVegetarian())
                                .collect(Collectors.toList())
                ));

        List<Recipe> filteredRecipesByVegan = recipeService.getAllRecipesByFilter(true, true, true, true, true, true, true, any(String[].class));
        List<Recipe> filteredRecipesByCategory = recipeService.getAllRecipesByFilter(false, true, true, true, false, true, false, any(String[].class));
        List<Recipe> filteredRecipesByNoneVeganVegetarian = recipeService.getAllRecipesByFilter(false, true, true, false, false, true, false, any(String[].class));
        assertEquals(1, filteredRecipesByVegan.size());
        assertEquals("Soup", filteredRecipesByVegan.get(0).getName());
        assertEquals(2, filteredRecipesByCategory.size());
        assertEquals("Spaghetti", filteredRecipesByCategory.get(0).getName());
        assertEquals("Soup", filteredRecipesByCategory.get(1).getName());
        assertEquals(1, filteredRecipesByNoneVeganVegetarian.size());
        assertEquals("Pancakes", filteredRecipesByNoneVeganVegetarian.get(0).getName());
        verify(recipeRepository, times(2)).findAllByJsonParameters(anyInt(), anyList(), anyBoolean(), anyBoolean());
    }
}
