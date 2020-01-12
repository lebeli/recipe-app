package com.hdmstuttgart.fluffybear;

import com.hdmstuttgart.fluffybear.model.Ingredient;
import com.hdmstuttgart.fluffybear.repository.IngredientRepository;
import com.hdmstuttgart.fluffybear.service.IngredientService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
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

    private List<Ingredient> allIngredients = new ArrayList<>();
    private Ingredient tomatoes;
    private Ingredient meat;
    private Ingredient flour;

    @Before
    public void setUpEntities() {
        tomatoes = new Ingredient("Tomatoes");
        meat = new Ingredient("Meat");
        flour = new Ingredient("Flour");
        tomatoes.setId(1);
        meat.setId(2);
        flour.setId(3);
        allIngredients.add(tomatoes);
        allIngredients.add(meat);
        allIngredients.add(flour);
    }

    @Test
    public void getAllIngredients() {
        when(ingredientRepository.findAll()).thenReturn(allIngredients);
        assertEquals(3, ingredientService.getAllIngredients().size());
        verify(ingredientRepository, times(1)).findAll();
    }

    @Test
    public void getIngredientByIdTest() {
        when(ingredientRepository.findById(1)).thenReturn(tomatoes);
        when(ingredientRepository.findById(2)).thenReturn(meat);
        assertEquals(1, ingredientService.getIngredient(1).getId());
        assertEquals(2, ingredientService.getIngredient(2).getId());
        assertEquals("Tomatoes", ingredientService.getIngredient(1).getName());
        assertEquals("Meat", ingredientService.getIngredient(2).getName());
        verify(ingredientRepository, times(2)).findById(1);
        verify(ingredientRepository, times(2)).findById(2);
    }

    @Test
    public void getIngredientByNameTest() {
        Ingredient meat2 = new Ingredient("Meat");
        meat2.setId(4);

        when(ingredientRepository.findByName("Tomatoes")).thenReturn(new ArrayList<Ingredient>(Arrays.asList(tomatoes)));
        when(ingredientRepository.findByName("Meat")).thenReturn(new ArrayList<Ingredient>(Arrays.asList(meat, meat2)));

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
        ingredientService.addIngredient(tomatoes);
        ingredientService.updateIngredient(1, tomatoes);
        verify(ingredientRepository, times(2)).save(tomatoes);
    }

    @Test
    public void deleteIngredientTest() {
        ingredientService.deleteIngredient(1);
        verify(ingredientRepository, times(1)).deleteById(1L);
    }
}

