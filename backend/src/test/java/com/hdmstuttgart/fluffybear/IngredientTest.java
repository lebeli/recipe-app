//package com.hdmstuttgart.fluffybear;
//
//import com.hdmstuttgart.fluffybear.model.Ingredient;
//import com.hdmstuttgart.fluffybear.model.Recipe;
//import com.hdmstuttgart.fluffybear.model.RecipeIngredient;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.JUnit4;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//
//// do not use SpringBootTest for unit testing (no need for spring boot application context)
//@RunWith(JUnit4.class)
//public class IngredientTest {
//
//    private Recipe recipeSpaghetti;
//    private Ingredient sauce;
//    private RecipeIngredient spaghettiSauce;
//
//    @Before
//    public void setUpEntities() {
//        recipeSpaghetti = new Recipe("Spaghetti");
//        sauce = new Ingredient("Sauce");
//        spaghettiSauce = new RecipeIngredient();
//
//        List<RecipeIngredient> spaghettiIngredients = new ArrayList<>();
//        List<RecipeIngredient> tortelliniIngredients = new ArrayList<>();
//        List<RecipeIngredient> sauceRecipes = new ArrayList<>();
//
//        spaghettiSauce.setRecipe(recipeSpaghetti);
//        spaghettiSauce.setIngredient(sauce);
//
////      spaghettiIngredients.add(spaghettiSauce);
////      tortelliniIngredients.add(tortelliniSauce);
//        sauceRecipes.add(spaghettiSauce);
//
////      recipeSpaghetti.setIngredients(spaghettiIngredients);
////      recipeTortellini.setIngredients(spaghettiIngredients);
//        sauce.setRecipes(sauceRecipes);
//    }
//
//    @Test
//    public void ingredientGetName() {
//        assertEquals("Sauce", sauce.getName());
//    }
//
//    @Test
//    public void ingredientSetName() {
//        sauce.setName("Sauce Hollandaise");
//        assertEquals("Sauce Hollandaise", sauce.getName());
//    }
//
//    @Test
//    public void ingredientGetRecipes() {
//        List<RecipeIngredient> recipes = new ArrayList<>();
//        recipes.add(spaghettiSauce);
//        assertTrue(recipes.equals(sauce.getRecipes()));
//    }
//
//    @Test
//    public void ingredientSetRecipes() {
//        List<RecipeIngredient> recipes = new ArrayList<>();
//        recipes.add(spaghettiSauce);
//        assertTrue(recipes.equals(sauce.getRecipes()));
//    }
//}
