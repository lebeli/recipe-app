package com.hdmstuttgart.fluffybear.controller;

import com.hdmstuttgart.fluffybear.Storage.StorageService;
import com.hdmstuttgart.fluffybear.TestUtilities;
import com.hdmstuttgart.fluffybear.model.Recipe;
import com.hdmstuttgart.fluffybear.service.IngredientService;
import com.hdmstuttgart.fluffybear.service.RecipeIngredientService;
import com.hdmstuttgart.fluffybear.service.RecipeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = RecipeController.class)  // includes only RecipeController in the application context
public class RecipeControllerTest {
    @Autowired
    private MockMvc httpRequest;

    @MockBean
    private RecipeService recipeService;

    @MockBean
    private IngredientService ingredientService;

    @MockBean
    private RecipeIngredientService recipeIngredientService;

    @MockBean
    private StorageService storageService;

    @Test
    public void recipeRequestRecieved() throws Exception {
        TestUtilities.addRecipesRequest(httpRequest, true)
                .andExpect(status().isOk());
    }

    @Test
    public void recipeRequestServiceCalled() throws Exception {
        TestUtilities.addRecipesRequest(httpRequest, true)
                .andExpect(status().isOk());
        verify(recipeService, times(1)).getAllRecipesByFilter(anyInt(), anyInt(), anyList(), anyBoolean(), anyBoolean());
    }

    @Test
    public void recipeRequestServiceCalledNoneVeganVegetarian() throws Exception {
        TestUtilities.addRecipesRequest(httpRequest, false)
                .andExpect(status().isOk());
        verify(recipeService, times(1)).getAllRecipesByFilterNoneVeganVegetarian(anyInt(), anyInt(), anyList());
    }

    @Test
    public void recipeRequestByIdRecieved() throws Exception {
        httpRequest.perform(get("/recipes/{id}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    public void recipeRequestByIdServiceCalled() throws Exception {
        httpRequest.perform(get("/recipes/{id}", 1L)
                .contentType("application/json"))
                .andExpect(status().isOk());
        verify(recipeService, times(1)).getRecipe(1L);

    }

    @Test
    public void recipeRequestAddRecieved() throws Exception {
        Recipe recipe = TestUtilities.buildRecipe("Spaghetti", Arrays.asList("Noodles", "Sauce"));

        httpRequest.perform(post("/recipes/add")
                .contentType("application/json")
                .content(TestUtilities.serialize(recipe)))
                .andExpect(status().isOk());
    }

    @Test
    public void recipeRequestAddServiceCalled() throws Exception {
        Recipe recipe = TestUtilities.buildRecipe("Spaghetti", Arrays.asList("Noodles", "Sauce"));

        httpRequest.perform(post("/recipes/add")
                .contentType("application/json")
                .content(TestUtilities.serialize(recipe)))
                .andExpect(status().isOk());
        verify(recipeService, times(1)).addRecipe(any(Recipe.class));
    }
}
