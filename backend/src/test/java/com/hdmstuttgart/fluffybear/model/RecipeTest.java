package com.hdmstuttgart.fluffybear.model;

import com.hdmstuttgart.fluffybear.TestUtilities;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;

// do not use SpringBootTest for unit testing (no need for spring boot application context)
@RunWith(JUnit4.class)
public class RecipeTest {

	private Recipe recipe;
	private Ingredient noodles;
	private Ingredient sauce;

	@Before
	public void setUpEntities() {
		recipe = TestUtilities.buildRecipe("Spaghetti", Arrays.asList("Noodles", "Sauce"));
		noodles = recipe.getIngredients().get(0).getIngredient();
		sauce = recipe.getIngredients().get(1).getIngredient();
	}

	@Test
	public void recipeGetName() {
		assertEquals("Spaghetti", recipe.getName());
	}

	@Test
	public void recipeSetName() {
		recipe.setName("Mac and Cheese");
		assertEquals("Mac and Cheese", recipe.getName());
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
}
