package com.hdmstuttgart.fluffybear.service;

import com.hdmstuttgart.fluffybear.model.Ingredient;
import com.hdmstuttgart.fluffybear.repository.IngredientRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        when(ingredientRepository.findById("Tomatoes")).thenReturn(Optional.of(tomatoes));
        when(ingredientRepository.findById("Meat")).thenReturn(Optional.of(meat));
        assertEquals("Tomatoes", ingredientService.getIngredient("Tomatoes").getId());
        assertEquals("Meat", ingredientService.getIngredient("Meat").getId());
        assertEquals("Tomatoes", ingredientService.getIngredient("Tomatoes").getName());
        assertEquals("Meat", ingredientService.getIngredient("Meat").getName());
        verify(ingredientRepository, times(2)).findById("Tomatoes");
        verify(ingredientRepository, times(2)).findById("Tomatoes");
    }

    @Test
    public void addAndUpdateIngredientTest() {
        ingredientService.addIngredient(tomatoes);
        ingredientService.updateIngredient(1, tomatoes);
        verify(ingredientRepository, times(2)).save(tomatoes);
    }

    @Test
    public void deleteIngredientTest() {
        ingredientService.deleteIngredient("Eier");
        verify(ingredientRepository, times(1)).deleteById("Eier");
    }
}

