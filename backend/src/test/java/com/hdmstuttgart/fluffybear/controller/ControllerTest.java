package com.hdmstuttgart.fluffybear.controller;

import com.hdmstuttgart.fluffybear.FluffyBearTestApplication;
import com.hdmstuttgart.fluffybear.repository.RecipeRepository;
import com.hdmstuttgart.fluffybear.service.IngredientService;
import com.hdmstuttgart.fluffybear.service.RecipeIngredientService;
import com.hdmstuttgart.fluffybear.service.RecipeService;
import com.hdmstuttgart.fluffybear.storage.DemoDataLoader;
import com.hdmstuttgart.fluffybear.storage.StorageService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes={FluffyBearTestApplication.class})
abstract class ControllerTest {

    @MockBean
    protected RecipeService recipeService;

    @MockBean
    protected IngredientService ingredientService;

    @MockBean
    protected RecipeIngredientService recipeIngredientService;

    @MockBean
    protected RecipeRepository recipeRepository;

    @MockBean
    protected StorageService storageService;

    @MockBean
    protected DemoDataLoader demoDataLoader;
}
