package com.hdmstuttgart.fluffybear;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.hdmstuttgart.fluffybear.model.Ingredient;
import com.hdmstuttgart.fluffybear.model.Recipe;
import com.hdmstuttgart.fluffybear.model.RecipeIngredient;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public final class TestUtilities {
    private TestUtilities() {}

    /**
     * Recipe builder for convenience.
     *
     * @param recipeName  Name of the recipe.
     * @param ingredientNames  List of names for ingredient generation.
     * @return  Recipe instance wir added ingredients.
     */
    public static Recipe buildRecipe(String recipeName, List<String> ingredientNames) {
        Recipe recipe = new Recipe(recipeName);
        ingredientNames.forEach(ingredientName -> {
            Ingredient ingredient = new Ingredient(ingredientName);
            recipe.addIngredient(ingredient);
            ingredient.setRecipes(recipe.getIngredients());
        });
        recipe.getIngredients().forEach(recipeIngredient -> {
            recipeIngredient.setTypeAmount("random");
        });
        return recipe;
    }

    /**
     * Serialize a generic entity for emulating the unmarshalling process in the controller.
     *
     * @param recipeComponent  Recipe or Ingredient entity.
     * @param <T>  Generic type, in this case Recipe or Ingredient.
     * @return  Serialized Recipe or Ingredient instance.
     * @throws JsonProcessingException
     */
    public static <T> String serialize(T recipeComponent) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(recipeComponent);
    }

    /**
     * Generate common GET request with meta data in header for recipe retrival.
     *
     * @param httpRequest  MockMvc instance for mocking http request.
     * @param vegan  Boolean, wether the recipe should be vegan or not
     * @return  ResultActions with which expected values can be tested.
     * @throws Exception
     */
    public static ResultActions allRecipesGetRequest(MockMvc httpRequest, Boolean vegan) throws Exception {
        return httpRequest.perform(get("/recipes/all")
                .header("breakfast", "true")
                .header("lunch", "true")
                .header("dinner", "true")
                .header("vegetarian", vegan.toString())
                .header("vegan", vegan.toString())
                .header("longTime", "true")
                .header("shortTime", "true")
                .contentType("application/json"));
    }

    /**
     * Generate common GET request with meta data in header for recipe retrival.
     *
     * @param httpRequest  MockMvc instance for mocking http request.
     * @param vegan  Boolean, wether the recipe should be vegan or not
     * @return  ResultActions with which expected values can be tested.
     * @throws Exception
     */
    public static ResultActions randomRecipeGetRequest(MockMvc httpRequest, Boolean vegan) throws Exception {
        return httpRequest.perform(get("/recipes/all")
                .header("breakfast", "true")
                .header("lunch", "true")
                .header("dinner", "true")
                .header("vegetarian", vegan.toString())
                .header("vegan", vegan.toString())
                .header("longTime", "true")
                .header("shortTime", "true")
                .contentType("application/json"));
    }
}
