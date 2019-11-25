package com.hdmstuttgart.fluffybear;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.hdmstuttgart.fluffybear.model.Ingredient;
import com.hdmstuttgart.fluffybear.model.Recipe;
import com.hdmstuttgart.fluffybear.repository.RecipeRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class ServiceRepositoryTest {
	
	@Autowired
    private TestEntityManager entityManager;
	
	@Autowired
	private RecipeRepository recipeRepository;
	
	@Test
	public void persistIngredient() {
		
		Ingredient noodles = new Ingredient("Noodles");
		Ingredient sauce = new Ingredient("Sauce");
		
		entityManager.persist(noodles);
		
//		Recipe spaghetti = new Recipe("Spaghetti", "Combine Noodles and Sauce.", 2);
	}
}
