package com.hdmstuttgart.fluffybear;

import com.hdmstuttgart.fluffybear.model.Ingredient;
import com.hdmstuttgart.fluffybear.model.Recipe;
import com.hdmstuttgart.fluffybear.model.RecipeIngredient;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

// do not use SpringBootTest for unit testing (no need for spring boot application context)
@RunWith(JUnit4.class)
public class RecipeTest {

	private Recipe recipe;
	private Ingredient noodles;
	private Ingredient sauce;
	private RecipeIngredient spaghettiNoodles;
	private RecipeIngredient spaghettiSauce;

	@Before
	public void setUpEntities() {
		recipe = new Recipe("Spaghetti", "Boil and add Sauce.", 3);
		noodles = new Ingredient("Noodles");
		sauce = new Ingredient("Sauce");
		spaghettiNoodles = new RecipeIngredient();
		spaghettiSauce = new RecipeIngredient();

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
	}

	@Test
	public void recipeGetName() {
		assertEquals("Spaghetti", recipe.getName());
	}

	@Test
	public void recipeGetInstruction() {
		assertEquals("Boil and add Sauce.", recipe.getDescription());
	}

	@Test
	public void recipeGetYield() {
		assertEquals(3, recipe.getYield());
	}

	@Test
	public void recipeSetName() {
		recipe.setName("Mac and Cheese");
		assertEquals("Mac and Cheese", recipe.getName());
	}

	@Test
	public void recipeSetInstruction() {
		recipe.setDescription("Boil and add Cheese.");
		assertEquals("Boil and add Cheese.", recipe.getDescription());
	}

	@Test
	public void recipeSetYield() {
		recipe.setYield(4);
		assertEquals(4, recipe.getYield());
	}

	@Test
	public void recipeAddIngredientSize() {
		Ingredient parmesan = new Ingredient("Parmesan");
		recipe.addIngredient(parmesan);
		assertEquals(3, recipe.getIngredients().size());
	}

	@Test
	public void recipeAddIngredientIdentity() {
		Ingredient parmesan = new Ingredient("Parmesan");
		recipe.addIngredient(parmesan);
		assertTrue(recipe.getIngredients().get(recipe.getIngredients().size() - 1).getIngredient().equals(parmesan));
	}

	@Test
	public void recipeRemoveIngredientSize() {
		recipe.removeIngredient(sauce);
		assertEquals(1, recipe.getIngredients().size());
	}

	@Test
	public void recipeRemoveIngredientIdentity() {
		recipe.removeIngredient(sauce);
		boolean ingredientInList = false;
		for(int i = 0; i < recipe.getIngredients().size(); i++) {
			if(sauce.equals(recipe.getIngredients().get(i).getIngredient())) {
				ingredientInList = true;
			}
		}
		assertFalse(ingredientInList);
	}

//	@Test
//	public void recipeSetRecipeInstructions() {
//		List<RecipeIngredient> recipeIngredients = new ArrayList<>();
//		recipeIngredients.add(recipeIngredients);
//		recipe.setIngredients(recipeIngredients);
//		assertEquals(recipe.getIngredients(), recipeIngredients);
//	}
//
//	@Test
//	public void recipeAddIngredients() {
//		List<Ingredient> ingredients = new ArrayList<>();
//		ingredients.add(noodles);
//		ingredients.add(sauce);
//		recipe.setIngredients(recipeIngredient);
//		assertEquals(recipe.getYield(), 4);
//	}

}
