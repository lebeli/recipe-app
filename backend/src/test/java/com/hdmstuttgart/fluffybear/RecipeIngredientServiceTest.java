package com.hdmstuttgart.fluffybear;

import com.hdmstuttgart.fluffybear.model.Ingredient;
import com.hdmstuttgart.fluffybear.model.Recipe;
import com.hdmstuttgart.fluffybear.model.RecipeIngredient;
import com.hdmstuttgart.fluffybear.model.RecipeIngredientKey;
import com.hdmstuttgart.fluffybear.repository.RecipeIngredientRepository;
import com.hdmstuttgart.fluffybear.service.RecipeIngredientService;
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
        garlic.setId(1);
        tomatoes.setId(2);
        meat.setId(3);
        spaghettiGarlic = new RecipeIngredient(spaghetti, garlic);
        spaghettiTomatoes = new RecipeIngredient(spaghetti, tomatoes);
        lasagnaTomatoes = new RecipeIngredient(lasagna, tomatoes);
        lasagnaMeat = new RecipeIngredient(lasagna, meat);
        spaghettiGarlic.setId(new RecipeIngredientKey(spaghetti.getId(), garlic.getId()));
        spaghettiTomatoes.setId(new RecipeIngredientKey(spaghetti.getId(), tomatoes.getId()));
        lasagnaTomatoes.setId(new RecipeIngredientKey(lasagna.getId(), tomatoes.getId()));
        lasagnaMeat.setId(new RecipeIngredientKey(lasagna.getId(), meat.getId()));
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

//    @Test
//    public void getRecipeIngredientsByIdTest() {
//        RecipeIngredientKey spaghettiGarlicKey = new RecipeIngredientKey(spaghetti.getId(), garlic.getId());
//        when(recipeIngredientRepository.findByKey(spaghettiGarlicKey)).thenReturn(
//                (RecipeIngredient) allRecipeIngredients
//                    .stream()
//                    .filter(recipeIngredient -> recipeIngredient.getId().getRecipeId() == 1 && recipeIngredient.getId().getIngredientId() == 1)
//                    .findFirst()
//                    .orElse(null)
//        );
//        RecipeIngredient queryRecipeIngredientKey = recipeIngredientService.getRecipeIngredient(spaghettiGarlicKey);
//        assertEquals("Spaghetti", queryRecipeIngredientKey.getRecipe().getName());
//        assertEquals("Garlic", queryRecipeIngredientKey.getIngredient().getName());
//        verify(recipeIngredientRepository, times(1)).findByKey(spaghettiGarlicKey);
//    }

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
                    .filter(recipeIngredient -> recipeIngredient.getIngredient().getName() == "Garlic")
                    .collect(Collectors.toList())
        );
        when(recipeIngredientRepository.findByIngredient(tomatoes)).thenReturn(
                allRecipeIngredients
                    .stream()
                    .filter(recipeIngredient -> recipeIngredient.getIngredient().getName() == "Tomatoes")
                    .collect(Collectors.toList())
        );

        List<RecipeIngredient> queryRecipeIngredientGarlic = recipeIngredientService.getRecipeIngredient(garlic);
        List<RecipeIngredient> queryRecipeIngredientTomatoes = recipeIngredientService.getRecipeIngredient(tomatoes);

        assertEquals(1, queryRecipeIngredientGarlic.size());
        assertEquals("Garlic", queryRecipeIngredientGarlic.get(0).getIngredient().getName());
        assertEquals(2, queryRecipeIngredientTomatoes.size());
        assertEquals("Tomatoes", queryRecipeIngredientTomatoes.get(0).getIngredient().getName());
        assertEquals("Tomatoes", queryRecipeIngredientTomatoes.get(1).getIngredient().getName());
        verify(recipeIngredientRepository, times(2)).findByIngredient(any(Ingredient.class));
    }

    @Test
    public void addAndUpdateRecipeIngredientTest() {
        RecipeIngredient lasagnaGarlic = new RecipeIngredient(lasagna, garlic);
        RecipeIngredientKey lasagnaGarlicKey = new RecipeIngredientKey(lasagna.getId(), garlic.getId());

        recipeIngredientService.addRecipeIngredient(lasagnaGarlic);
        recipeIngredientService.updateRecipeIngredient(lasagnaGarlicKey, lasagnaGarlic);

        verify(recipeIngredientRepository, times(2)).save(lasagnaGarlic);
    }

    @Test
    public void deleteRecipeTest() {
        Recipe recipe = new Recipe("Spaghetti");
        RecipeIngredientKey spaghettiGarlicKey = new RecipeIngredientKey(spaghetti.getId(), garlic.getId());
        recipeIngredientService.deleteRecipeIngredient(spaghettiGarlicKey);
        verify(recipeIngredientRepository, times(1)).deleteById(spaghettiGarlicKey);
    }
}
