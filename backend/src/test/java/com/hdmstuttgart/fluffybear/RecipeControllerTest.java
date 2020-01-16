package com.hdmstuttgart.fluffybear;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.hdmstuttgart.fluffybear.Storage.StorageService;
import com.hdmstuttgart.fluffybear.controller.RecipeController;
import com.hdmstuttgart.fluffybear.model.Ingredient;
import com.hdmstuttgart.fluffybear.model.Recipe;
import com.hdmstuttgart.fluffybear.model.RecipeIngredient;
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

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
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
        httpRequest.perform(get("/recipes")
                .header("breakfast", "true")
                .header("lunch", "true")
                .header("dinner", "true")
                .header("vegetarian", "true")
                .header("vegan", "true")
                .header("longTime", "true")
                .header("shortTime", "true")
                .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void recipeRequestServiceCalled() throws Exception {
        httpRequest.perform(get("/recipes")
                .header("breakfast", "true")
                .header("lunch", "false")
                .header("dinner", "true")
                .header("vegetarian", "true")
                .header("vegan", "false")
                .header("longTime", "false")
                .header("shortTime", "true")
                .contentType("application/json"))
                .andExpect(status().isOk());
        verify(recipeService, times(1)).getAllRecipesByFilter(anyInt(), anyInt(), anyList(), anyBoolean(), anyBoolean());
    }

    @Test
    public void recipeRequestServiceCalledNoneVeganVegetarian() throws Exception {
        httpRequest.perform(get("/recipes")
                .header("breakfast", "true")
                .header("lunch", "false")
                .header("dinner", "true")
                .header("vegetarian", "false")
                .header("vegan", "false")
                .header("longTime", "false")
                .header("shortTime", "true")
                .contentType("application/json"))
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
        verify(recipeService, times(1)).getRecipe(1);

    }

    @Test
    public void recipeRequestAddRecieved() throws Exception {
        Recipe recipe = new Recipe("Spaghetti");
        Ingredient noodles = new Ingredient("Noodles");
        Ingredient sauce = new Ingredient("Sauce");
        RecipeIngredient spaghettiNoodles = new RecipeIngredient();
        RecipeIngredient spaghettiSauce = new RecipeIngredient();

        List<RecipeIngredient> spaghettiIngredients = new ArrayList<>();
        List<RecipeIngredient> noodleRecipes = new ArrayList<>();
        List<RecipeIngredient> sauceRecipes = new ArrayList<>();

        spaghettiNoodles.setRecipe(recipe);
        spaghettiNoodles.setIngredient(noodles);
        spaghettiSauce.setRecipe(recipe);
        spaghettiSauce.setIngredient(sauce);

        spaghettiIngredients.add(spaghettiNoodles);
        spaghettiIngredients.add(spaghettiSauce);
        noodleRecipes.add(spaghettiNoodles);
        sauceRecipes.add(spaghettiSauce);

        recipe.setIngredients(spaghettiIngredients);
        noodles.setRecipes(noodleRecipes);
        sauce.setRecipes(sauceRecipes);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(recipe);

        httpRequest.perform(post("/recipes/add")
                .contentType("application/json")
                .content(requestJson))
                .andExpect(status().isOk());
    }

    @Test
    public void recipeRequestAddServiceCalled() throws Exception {
        Recipe recipe = new Recipe("Spaghetti");
        Ingredient noodles = new Ingredient("Noodles");
        Ingredient sauce = new Ingredient("Sauce");
        RecipeIngredient spaghettiNoodles = new RecipeIngredient();
        RecipeIngredient spaghettiSauce = new RecipeIngredient();

        List<RecipeIngredient> spaghettiIngredients = new ArrayList<>();
        List<RecipeIngredient> noodleRecipes = new ArrayList<>();
        List<RecipeIngredient> sauceRecipes = new ArrayList<>();

        spaghettiNoodles.setRecipe(recipe);
        spaghettiNoodles.setIngredient(noodles);
        spaghettiSauce.setRecipe(recipe);
        spaghettiSauce.setIngredient(sauce);

        spaghettiIngredients.add(spaghettiNoodles);
        spaghettiIngredients.add(spaghettiSauce);
        noodleRecipes.add(spaghettiNoodles);
        sauceRecipes.add(spaghettiSauce);

        recipe.setIngredients(spaghettiIngredients);
        noodles.setRecipes(noodleRecipes);
        sauce.setRecipes(sauceRecipes);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(recipe);

        httpRequest.perform(post("/recipes/add")
                .contentType("application/json")
                .content(requestJson))
                .andExpect(status().isOk()); // TODO: check if recipe correctly unmarshalled
        verify(recipeService, times(1)).addRecipe(any(Recipe.class));

    }

    @Test
    public void ingredientsRequestRecieved() throws Exception {
        httpRequest.perform(get("/ingredients", 1L))
                .andExpect(status().isOk());
    }

    @Test
    public void ingredientsRequestServiceCalled() throws Exception {
        httpRequest.perform(get("/ingredients", 1L))
                .andExpect(status().isOk());
        verify(ingredientService, times(1)).getAllIngredients();
    }


}
