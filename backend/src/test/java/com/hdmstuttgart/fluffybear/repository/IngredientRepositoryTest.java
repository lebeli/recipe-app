package com.hdmstuttgart.fluffybear.repository;

import com.hdmstuttgart.fluffybear.model.Ingredient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
        assertEquals(147, ingredientRepository.count());
    }

    @Test
    public void saveIngredientTest() {
        long initialSize = ingredientRepository.count();
        Ingredient ingredientTest = new Ingredient("Test");
        ingredientRepository.save(ingredientTest);
        assertEquals(initialSize+1, ingredientRepository.count());
        assertEquals(ingredientTest.getId(), ingredientRepository.findById("Test").get().getId());
    }

    @Test
    public void saveDuplicateTest() {
        long initialSize = ingredientRepository.count();
        ingredientRepository.save(ingredientRepository.findById("Eier").get());
        assertEquals(initialSize, ingredientRepository.count());
    }

    @Test
    public void deleteIngredientTest() {
        long initialSize = ingredientRepository.count();
        ingredientRepository.delete(ingredientRepository.findById("Eier").get());
        assertEquals(initialSize-1, ingredientRepository.count());
    }
}
