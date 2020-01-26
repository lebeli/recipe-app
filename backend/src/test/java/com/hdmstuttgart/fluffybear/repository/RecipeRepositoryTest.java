package com.hdmstuttgart.fluffybear.repository;

import com.hdmstuttgart.fluffybear.model.Recipe;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase // embedded db
@Transactional // db rollback after each test
public class RecipeRepositoryTest {
    @Autowired
    private RecipeRepository recipeRepository;

    private List<Recipe> queryResult;

    @Before
    public void setUp() {
        queryResult = new ArrayList<>();
    }

    @Test
    public void repositoryInitTest() {
        recipeRepository.findAll().forEach(recipe -> {
            queryResult.add(recipe);
        });
        assertEquals(31, queryResult.size());
    }

    @Test
    public void saveRecipeTest() {
        Recipe recipeTest = new Recipe("Test");
        recipeRepository.save(recipeTest);
        recipeRepository.findByName("Test").forEach(recipe -> {
            queryResult.add(recipe);
        });
        assertEquals("Test", queryResult.get(0).getName());
    }

    @Test
    public void saveDuplicateTest() {
        recipeRepository.findByName("Mickriges Müsli").forEach(recipe -> {
            queryResult.add(recipe);
        });
        recipeRepository.save(queryResult.get(0));
        queryResult.clear();
        recipeRepository.findAll().forEach(recipe -> {
            queryResult.add(recipe);
        });
        assertEquals(31, queryResult.size());
    }

    @Test
    public void deleteRecipeTest() {
        recipeRepository.deleteById(1L);
        recipeRepository.findAll().forEach(recipe -> {
            queryResult.add(recipe);
        });
        assertEquals(30, queryResult.size());
    }

    @Test
    public void findByJsonParameters() {
        recipeRepository.findByJsonParameters(45, new ArrayList<>(Arrays.asList("breakfast")), false, false).forEach(recipe -> {
            queryResult.add(recipe);
        });
        assertEquals("Frivole Frühstückseier mit Pfiff", queryResult.get(0).getName());
    }
}
