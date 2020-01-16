package com.hdmstuttgart.fluffybear;

import com.hdmstuttgart.fluffybear.Storage.StorageService;
import com.hdmstuttgart.fluffybear.controller.IngredientController;
import com.hdmstuttgart.fluffybear.model.Ingredient;
import com.hdmstuttgart.fluffybear.service.IngredientService;
import com.hdmstuttgart.fluffybear.service.RecipeIngredientService;
import com.hdmstuttgart.fluffybear.service.RecipeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = IngredientController.class)
public class IngredientControllerTest {
    @Autowired
    private MockMvc httpRequest;

    @MockBean
    private RecipeService recipeService;

    @MockBean
    private IngredientService ingredientService;


    @MockBean
    private StorageService storageService;

    @Test
    public void ingredientsRequestRecieved() throws Exception {
        httpRequest.perform(get("/ingredients"))
                .andExpect(status().isOk());
    }

    @Test
    public void ingredientsRequestServiceCalled() throws Exception {
        httpRequest.perform(get("/ingredients"))
                .andExpect(status().isOk());
        verify(ingredientService, times(1)).getAllIngredients();
    }
}
