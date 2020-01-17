//package com.hdmstuttgart.fluffybear.repository;
//
//import com.hdmstuttgart.fluffybear.TestUtilities;
//import com.hdmstuttgart.fluffybear.model.Ingredient;
//import com.hdmstuttgart.fluffybear.model.Recipe;
//import com.hdmstuttgart.fluffybear.model.RecipeIngredient;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//@AutoConfigureTestDatabase
//@Transactional
//public class RecipeIngredientRepositoryTest {
//    @Autowired
//    private RecipeIngredientRepository recipeIngredientRepository;
//
//    private List<Ingredient> queryResult;
//
//    @Before
//    public void setUp() {
//        queryResult = new ArrayList<>();
//    }
//
//    @Test
//    public void repositoryInitTest() {
//        assertEquals(147, TestUtilities.repositorySize(recipeIngredientRepository));
//    }
//
//    @Test
//    public void saveIngredientTest() {
//        int initialSize = TestUtilities.repositorySize(recipeIngredientRepository);
//        Recipe recipe = TestUtilities.buildRecipe("Test", Arrays.asList("ingredient"));
//        RecipeIngredient recipeIngredientTest = recipe.getIngredients().get(0);
//        recipeIngredientRepository.save(recipe.getIngredients().get(0));
//        assertEquals(initialSize+1, TestUtilities.repositorySize(recipeIngredientRepository));
////        assertEquals("Test", recipeIngredientRepository.findByRecipe(recipe));
//    }
//
////    @Test
////    public void saveDuplicateTest() {
////        int initialSize = TestUtilities.repositorySize(recipeIngredientRepository);
////        recipeIngredientRepository.save(recipeIngredientRepository.findById("Eier").get());
////        assertEquals(initialSize, TestUtilities.repositorySize(recipeIngredientRepository));
////    }
////
////    @Test
////    public void deleteRecipeIngredientTest() {
////        int initialSize = TestUtilities.repositorySize(recipeIngredientRepository);
////        recipeIngredientRepository.delete(recipeIngredientRepository.findById(0L).get());
////        assertEquals(initialSize-1, TestUtilities.repositorySize(recipeIngredientRepository));
////    }
//}
