package com.hdmstuttgart.fluffybear;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.hdmstuttgart.fluffybear.model.Ingredient;
import com.hdmstuttgart.fluffybear.model.Recipe;
import com.hdmstuttgart.fluffybear.model.RecipeIngredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public final class TestUtilities {
    private TestUtilities() {}

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

    public static <T> String serialize(T recipeComponent) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(recipeComponent);
    }

    public static ResultActions addRecipesRequest(MockMvc httpRequest, Boolean vegan) throws Exception {
        return httpRequest.perform(get("/recipes")
                .header("breakfast", "true")
                .header("lunch", "true")
                .header("dinner", "true")
                .header("vegetarian", vegan.toString())
                .header("vegan", vegan.toString())
                .header("longTime", "true")
                .header("shortTime", "true")
                .contentType("application/json"));
    }

    public static int repositorySize(CrudRepository repository) {
        int size = 0;
        Iterator it = repository.findAll().iterator();
        do {
            size++;
            if(it.hasNext()) {it.next();}
        } while (it.hasNext());
        return size;
    }
}
