package com.hdmstuttgart.fluffybear.repository;

import com.hdmstuttgart.fluffybear.TestUtilities;
import com.hdmstuttgart.fluffybear.model.Ingredient;
import com.hdmstuttgart.fluffybear.model.Recipe;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase
@Transactional
public class IngredientRepositoryTest {
    @Autowired
    private IngredientRepository ingredientRepository;

    private List<Ingredient> queryResult;

    @Before
    public void setUp() {
        queryResult = new ArrayList<>();
    }

    @Test
    public void repositoryInitTest() {
        assertEquals(147, TestUtilities.repositorySize(ingredientRepository));
    }

    @Test
    public void saveIngredientTest() {
        int initialSize = TestUtilities.repositorySize(ingredientRepository);
        Ingredient ingredientTest = new Ingredient("Test");
        ingredientRepository.save(ingredientTest);
        assertEquals(initialSize+1, TestUtilities.repositorySize(ingredientRepository));
        assertEquals(ingredientTest.getName(), ingredientRepository.findById("Test").get().getName());
    }

    @Test
    public void saveDuplicateTest() {
        int initialSize = TestUtilities.repositorySize(ingredientRepository);
        ingredientRepository.save(ingredientRepository.findById("Eier").get());
        assertEquals(initialSize, TestUtilities.repositorySize(ingredientRepository));
    }

    @Test
    public void deleteIngredientTest() {
        int initialSize = TestUtilities.repositorySize(ingredientRepository);
        ingredientRepository.delete(ingredientRepository.findById("Eier").get());
        assertEquals(initialSize-1, TestUtilities.repositorySize(ingredientRepository));
    }
}
