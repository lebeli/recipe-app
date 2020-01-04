package com.hdmstuttgart.fluffybear;

import com.hdmstuttgart.fluffybear.model.Ingredient;
import com.hdmstuttgart.fluffybear.model.Recipe;
import com.hdmstuttgart.fluffybear.repository.IngredientRepository;
import com.hdmstuttgart.fluffybear.service.IngredientService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class IngredientServiceTest {
    @InjectMocks
    IngredientService ingredientService;

    @Mock
    IngredientRepository ingredientRepository;

    @Test
    public void getAllIngredients() {
        List<Ingredient> allIngredients = new ArrayList<>();
        Ingredient ingredient1 = new Ingredient("Tomatoes");
        Ingredient ingredient2 = new Ingredient("Meat");
        Ingredient ingredient3 = new Ingredient("Flour");
        allIngredients.add(ingredient1);
        allIngredients.add(ingredient2);
        allIngredients.add(ingredient3);

        when(ingredientRepository.findAll()).thenReturn(allIngredients);

        assertEquals(3, ingredientService.getAllIngredients().size());
        verify(ingredientRepository, times(1)).findAll();
    }

    @Test
    public void getIngredientByIdTest() {
        Ingredient ingredient1 = new Ingredient("Tomatoes");
        Ingredient ingredient2 = new Ingredient("Meat");
        ingredient1.setId(1);
        ingredient2.setId(2);

        when(ingredientRepository.findById(1)).thenReturn(ingredient1);
        when(ingredientRepository.findById(2)).thenReturn(ingredient2);

        assertEquals(1, ingredientService.getIngredient(1).getId());
        assertEquals(2, ingredientService.getIngredient(2).getId());
        assertEquals("Tomatoes", ingredientService.getIngredient(1).getName());
        assertEquals("Meat", ingredientService.getIngredient(2).getName());
        verify(ingredientRepository, times(2)).findById(1);
        verify(ingredientRepository, times(2)).findById(2);
    }

    @Test
    public void getIngredientByNameTest() {
        Ingredient ingredient1 = new Ingredient("Tomatoes");
        Ingredient ingredient2 = new Ingredient("Meat");
        Ingredient ingredient3 = new Ingredient("Meat");
        ingredient1.setId(1);
        ingredient2.setId(2);
        ingredient3.setId(3);

        when(ingredientRepository.findByName("Tomatoes")).thenReturn(new ArrayList<Ingredient>(Arrays.asList(ingredient1)));
        when(ingredientRepository.findByName("Meat")).thenReturn(new ArrayList<Ingredient>(Arrays.asList(ingredient2, ingredient3)));

        List<Ingredient> tomatoesQuery = ingredientService.getIngredient("Tomatoes");
        List<Ingredient> meatQuery = ingredientService.getIngredient("Meat");

        assertEquals(1, tomatoesQuery.size());
        assertEquals(2, meatQuery.size());
        assertEquals("Tomatoes", tomatoesQuery.get(0).getName());
        assertEquals("Meat", meatQuery.get(0).getName());
        assertEquals("Meat", meatQuery.get(1).getName());
        verify(ingredientRepository, times(1)).findByName("Tomatoes");
        verify(ingredientRepository, times(1)).findByName("Meat");
    }

    @Test
    public void addAndUpdateIngredientTest() {
        Ingredient ingredient = new Ingredient("Tomatoes");

        ingredientService.addIngredient(ingredient);
        ingredientService.updateIngredient(1, ingredient);

        verify(ingredientRepository, times(2)).save(ingredient);
    }

    @Test
    public void deleteRecipeTest() {
        Recipe recipe = new Recipe("Tomatoes");
        ingredientService.deleteIngredient(1);
        verify(ingredientRepository, times(1)).deleteById(1L);
    }
}

