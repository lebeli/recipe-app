package com.hdmstuttgart.fluffybear;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hdmstuttgart.fluffybear.model.Ingredient;
import com.hdmstuttgart.fluffybear.model.Recipe;
import com.hdmstuttgart.fluffybear.model.RecipeIngredient;
import com.hdmstuttgart.fluffybear.repository.IngredientRepository;
import com.hdmstuttgart.fluffybear.repository.RecipeIngredientRepository;
import com.hdmstuttgart.fluffybear.repository.RecipeRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Iterator;

@Component
public class DataLoader implements ApplicationRunner {

    private ObjectMapper mapper = new ObjectMapper();

    private RecipeRepository recipeRepository;
    private IngredientRepository ingredientRepository;
    private RecipeIngredientRepository recipeIngredientRepository;

    @Autowired
    public DataLoader(RecipeRepository recipeRepository, IngredientRepository ingredientRepository, RecipeIngredientRepository recipeIngredientRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.recipeIngredientRepository = recipeIngredientRepository;
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
            recipeJSON.put("image", "localhost:8080/image/" + currentIndex + ".jpg"); // set image path
            try {
                // Map recipeJSON to recipe object
                Recipe recipe = mapper.readValue(recipeJSON.toString(), Recipe.class);
                recipeRepository.save(recipe);
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
            Ingredient ingredient = ingredientRepository.save(mapper.readValue(ingredientJSON.toString(), Ingredient.class));
            recipeIngredientRepository.save(new RecipeIngredient(recipe, ingredient, typeAmount));
        }
    }

    static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
