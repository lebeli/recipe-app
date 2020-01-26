package com.hdmstuttgart.fluffybear.controller;

import com.hdmstuttgart.fluffybear.storage.StorageService;
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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = RecipeController.class)
public class RecipeControllerTest extends ControllerTest {
    @Autowired
    protected MockMvc httpRequest;

    @Test
    public void allRecipesRequestRecieved() throws Exception {
        TestUtilities.allRecipesGetRequest(httpRequest, true, new String[] {"Mehl"})
                .andExpect(status().isOk());
    }

    @Test
    public void allRecipesRequestServiceCalled() throws Exception {
        TestUtilities.allRecipesGetRequest(httpRequest, true, new String[] {"Mehl"})
                .andExpect(status().isOk());
        verify(recipeService, times(1)).getAllRecipesByFilter(anyBoolean(), anyBoolean(), anyBoolean(), anyBoolean(), anyBoolean(), anyBoolean(), anyBoolean(), any(String[].class));
    }

    @Test
    public void allRecipesRequestServiceCalledNoneVeganVegetarian() throws Exception {
        TestUtilities.allRecipesGetRequest(httpRequest, false, new String[] {"Mehl"})
                .andExpect(status().isOk());
        verify(recipeService, times(1)).getAllRecipesByFilter(anyBoolean(), anyBoolean(), anyBoolean(), anyBoolean(), anyBoolean(), anyBoolean(), anyBoolean(), any(String[].class));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void randomRecipeRequestRecieved() throws Exception {
        TestUtilities.randomRecipeGetRequest(httpRequest, true, new String[] {"Mehl"})
                .andExpect(status().isOk());
    }

    @Test
    public void randomRecipeRequestServiceCalled() throws Exception {
        TestUtilities.randomRecipeGetRequest(httpRequest, true, new String[] {"Mehl"})
                .andExpect(status().isOk());
        verify(recipeService, times(1)).getOneRecipeByFilter(anyBoolean(), anyBoolean(), anyBoolean(), anyBoolean(), anyBoolean(), anyBoolean(), anyBoolean(), any(String[].class));
    }

    @Test
    public void randomRecipeRequestServiceCalledNoneVeganVegetarian() throws Exception {
        TestUtilities.randomRecipeGetRequest(httpRequest, false, new String[] {"Mehl"})
                .andExpect(status().isOk());
        verify(recipeService, times(1)).getOneRecipeByFilter(anyBoolean(), anyBoolean(), anyBoolean(), anyBoolean(), anyBoolean(), anyBoolean(), anyBoolean(), any(String[].class));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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
        Recipe recipe = TestUtilities.buildRecipe("Spaghetti", Arrays.asList("Noodles", "Sauce"), "dinner", 30);

        httpRequest.perform(post("/recipes/add")
                .contentType("application/json")
                .content(TestUtilities.serialize(recipe)))
                .andExpect(status().isOk());
    }

    @Test
    public void recipeRequestAddServiceCalled() throws Exception {
        Recipe recipe = TestUtilities.buildRecipe("Spaghetti", Arrays.asList("Noodles", "Sauce"), "dinner", 30);

        httpRequest.perform(post("/recipes/add")
                .contentType("application/json")
                .content(TestUtilities.serialize(recipe)))
                .andExpect(status().isOk());
        verify(recipeService, times(1)).addRecipe(any(Recipe.class));
    }
}
