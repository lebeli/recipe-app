package com.hdmstuttgart.fluffybear.Storage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hdmstuttgart.fluffybear.model.Ingredient;
import com.hdmstuttgart.fluffybear.model.Recipe;
import com.hdmstuttgart.fluffybear.model.RecipeIngredient;
import com.hdmstuttgart.fluffybear.service.IngredientService;
import com.hdmstuttgart.fluffybear.service.RecipeIngredientService;
import com.hdmstuttgart.fluffybear.service.RecipeService;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Iterator;

/**
 * Loads 30 demo recipes from demo_recipes file into connected database.
 */
@Component
public class DemoDataLoader implements ApplicationRunner {
    private final static Logger logger = Logger.getLogger(FileSystemStorageService.class);

    /**
     * Maps json data to java classes
     */
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

    /**
     * Called after application successfully launched.
     * Executes logic for reading recipes from demo_recipes files and inserting them into database.
     *
     * @param args
     */
    public void run(ApplicationArguments args) {
        logger.info("Started loading demo data");
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
        logger.info("Finished loading demo data");
    }

    /**
     * Adds every single ingredient from the parameter array to the parameter recipe.
     *
     * @param ingredientArray containts all ingredients to be added
     * @param recipe in which the ingredients will be inserted
     * @throws IOException if mapping json to ingredient class fails
     */
    private void addIngredients(JSONArray ingredientArray, Recipe recipe) throws IOException {
        Iterator<Object> ingredientIterator = ingredientArray.iterator();
        while (ingredientIterator.hasNext()) {
            JSONObject ingredientJSON = (JSONObject) ingredientIterator.next();
            String typeAmount = ingredientJSON.getString("typeAmount");
            ingredientJSON.remove("typeAmount");
            Ingredient ingredient = mapper.readValue(ingredientJSON.toString(), Ingredient.class);

            RecipeIngredient recipeIngredient = new RecipeIngredient(recipe, ingredient, typeAmount);
            recipe.getIngredients().add(recipeIngredient);
            ingredient.getRecipes().add(recipeIngredient);

            recipeService.addRecipe(recipe);
            ingredientService.addIngredient(ingredient);
            recipeIngredientService.addRecipeIngredient(recipeIngredient);
        }
    }

    /**
     * Converts Inputstream contents into a simple String.
     *
     * @param is the inputstream to be converted
     * @return the converted string
     */
    private static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
