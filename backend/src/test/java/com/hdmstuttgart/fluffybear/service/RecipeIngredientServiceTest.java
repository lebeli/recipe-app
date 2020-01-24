package com.hdmstuttgart.fluffybear.service;

import com.hdmstuttgart.fluffybear.model.Ingredient;
import com.hdmstuttgart.fluffybear.model.Recipe;
import com.hdmstuttgart.fluffybear.model.RecipeIngredient;
import com.hdmstuttgart.fluffybear.repository.RecipeIngredientRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RecipeIngredientServiceTest {
    @InjectMocks
    private RecipeIngredientService recipeIngredientService;

    @Mock
    private RecipeIngredientRepository recipeIngredientRepository;

    private List<RecipeIngredient> allRecipeIngredients = new ArrayList<>();
    private Recipe spaghetti;
    private Recipe lasagna;
    private Ingredient garlic;
    private Ingredient tomatoes;
    private Ingredient meat;
    private RecipeIngredient spaghettiGarlic;
    private RecipeIngredient spaghettiTomatoes;
    private RecipeIngredient lasagnaTomatoes;
    private RecipeIngredient lasagnaMeat;

    @Before
    public void setUpEntities() {
        spaghetti = new Recipe("Spaghetti");
        lasagna = new Recipe("Lasagna");
        garlic = new Ingredient("Garlic");
        tomatoes = new Ingredient("Tomatoes");
        meat = new Ingredient("Meat");
        spaghetti.setId(1);
        lasagna.setId(2);
        spaghettiGarlic = new RecipeIngredient(spaghetti, garlic);
        spaghettiTomatoes = new RecipeIngredient(spaghetti, tomatoes);
        lasagnaTomatoes = new RecipeIngredient(lasagna, tomatoes);
        lasagnaMeat = new RecipeIngredient(lasagna, meat);
        spaghettiGarlic.setId(1);
        spaghettiTomatoes.setId(2);
        lasagnaTomatoes.setId(3);
        lasagnaMeat.setId(4);
        allRecipeIngredients.add(spaghettiGarlic);
        allRecipeIngredients.add(spaghettiTomatoes);
        allRecipeIngredients.add(lasagnaTomatoes);
        allRecipeIngredients.add(lasagnaMeat);
    }

    @Test
    public void getAllRecipeIngredientsTest() {
        when(recipeIngredientRepository.findAll()).thenReturn(allRecipeIngredients);
        assertEquals(4, recipeIngredientService.getAllRecipeIngredients().size());
        verify(recipeIngredientRepository, times(1)).findAll();
    }


    @Test
    public void getRecipeIngredientsByRecipeTest() {
        when(recipeIngredientRepository.findByRecipe(spaghetti)).thenReturn(
                allRecipeIngredients
                        .stream()
                        .filter(recipeIngredient -> recipeIngredient.getRecipe().getName() == "Spaghetti")
                        .collect(Collectors.toList())
        );
        List<RecipeIngredient> queryRecipeIngredient = recipeIngredientService.getRecipeIngredient(spaghetti);
        assertEquals(2, queryRecipeIngredient.size());
        assertEquals("Spaghetti", queryRecipeIngredient.get(0).getRecipe().getName());
        assertEquals("Spaghetti", queryRecipeIngredient.get(1).getRecipe().getName());
        verify(recipeIngredientRepository, times(1)).findByRecipe(spaghetti);
    }

    @Test
    public void getRecipeIngredientsByIngredientTest() {
        when(recipeIngredientRepository.findByIngredient(garlic)).thenReturn(
                allRecipeIngredients
                    .stream()
                    .filter(recipeIngredient -> recipeIngredient.getIngredient().getId() == "Garlic")
                    .collect(Collectors.toList())
        );
        when(recipeIngredientRepository.findByIngredient(tomatoes)).thenReturn(
                allRecipeIngredients
                    .stream()
                    .filter(recipeIngredient -> recipeIngredient.getIngredient().getId() == "Tomatoes")
                    .collect(Collectors.toList())
        );

        List<RecipeIngredient> queryRecipeIngredientGarlic = recipeIngredientService.getRecipeIngredient(garlic);
        List<RecipeIngredient> queryRecipeIngredientTomatoes = recipeIngredientService.getRecipeIngredient(tomatoes);

        assertEquals(1, queryRecipeIngredientGarlic.size());
        assertEquals("Garlic", queryRecipeIngredientGarlic.get(0).getIngredient().getId());
        assertEquals(2, queryRecipeIngredientTomatoes.size());
        assertEquals("Tomatoes", queryRecipeIngredientTomatoes.get(0).getIngredient().getId());
        assertEquals("Tomatoes", queryRecipeIngredientTomatoes.get(1).getIngredient().getId());
        verify(recipeIngredientRepository, times(2)).findByIngredient(any(Ingredient.class));
    }

    @Test
    public void addAndUpdateRecipeIngredientTest() {
        RecipeIngredient lasagnaGarlic = new RecipeIngredient(lasagna, garlic);
        lasagnaGarlic.setId(1);
        recipeIngredientService.addRecipeIngredient(lasagnaGarlic);
        recipeIngredientService.updateRecipeIngredient(lasagnaGarlic);
        verify(recipeIngredientRepository, times(2)).save(lasagnaGarlic);
    }

    @Test
    public void deleteRecipeTest() {
        long id = allRecipeIngredients.get(0).getId();
        recipeIngredientService.deleteRecipeIngredient(id);
        verify(recipeIngredientRepository, times(1)).deleteById(id);
    }
}
