package com.hdmstuttgart.fluffybear.Storage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hdmstuttgart.fluffybear.model.Ingredient;
import com.hdmstuttgart.fluffybear.model.Recipe;
import com.hdmstuttgart.fluffybear.model.RecipeIngredient;
import com.hdmstuttgart.fluffybear.service.IngredientService;
import com.hdmstuttgart.fluffybear.service.RecipeIngredientService;
import com.hdmstuttgart.fluffybear.service.RecipeService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Iterator;

@Component
public class DemoDataLoader implements ApplicationRunner {

    private final ObjectMapper mapper = new ObjectMapper();

    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final RecipeIngredientService recipeIngredientService;

    @Autowired
    public DemoDataLoader(RecipeService recipeService, IngredientService ingredientService, RecipeIngredientService recipeIngredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.recipeIngredientService = recipeIngredientService;
    }

    public void run(ApplicationArguments args) {
        InputStream path = getClass().getClassLoader().getResourceAsStream("demo_recipes.json");
        String jsonString = convertStreamToString(path);
        JSONArray recipeArray = new JSONArray(jsonString);

        Iterator<Object> iterator = recipeArray.iterator();
        int currentIndex = 1;
        while(iterator.hasNext()) {
            JSONObject recipeJSON = (JSONObject) iterator.next();

            // Save ingredients
            JSONArray ingredientArray = (JSONArray) recipeJSON.get("ingredients");
            recipeJSON.remove("ingredients"); // Later added via repository
            recipeJSON.put("image", "localhost/api/images/" + currentIndex + ".jpg"); // set image path
            try {
                // Map recipeJSON to recipe object
                Recipe recipe = recipeService.addRecipe(mapper.readValue(recipeJSON.toString(), Recipe.class));
                addIngredients(ingredientArray, recipe);
            } catch (IOException e) {
                e.printStackTrace();
            }
            currentIndex++;
        }
    }

    private void addIngredients(JSONArray ingredientArray, Recipe recipe) throws IOException {
        Iterator<Object> ingredientIterator = ingredientArray.iterator();
        while (ingredientIterator.hasNext()) {
            JSONObject ingredientJSON = (JSONObject) ingredientIterator.next();
            String typeAmount = ingredientJSON.getString("typeAmount");
            ingredientJSON.remove("typeAmount");
            Ingredient ingredient = mapper.readValue(ingredientJSON.toString(), Ingredient.class);
            ingredient.setId(ingredient.getName());
            ingredientService.addIngredient(ingredient);
            recipeIngredientService.addRecipeIngredient(new RecipeIngredient(recipe, ingredient, typeAmount));
        }
    }

    static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
