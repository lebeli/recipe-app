package com.hdmstuttgart.fluffybear.controller;


import com.hdmstuttgart.fluffybear.Storage.FileSystemStorageService;
import com.hdmstuttgart.fluffybear.model.Ingredient;
import com.hdmstuttgart.fluffybear.service.IngredientService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller Class for handling ingredient requests and saving/returning ingredients.
 */
@RestController
public class IngredientController {
    private final static Logger logger = Logger.getLogger(FileSystemStorageService.class);

    @Autowired
    IngredientService ingredientService;

    @RequestMapping("/ingredients")
    public List<String> getIngredients() {
        logger.info("Ingredient request performed.");
        return ingredientService.getAllIngredients().stream().map(ingredient -> ingredient.getId()).collect(Collectors.toList());
    }

    /**
     * Handler for /ingredients/add resource. Reads JSON from POST request body and adds a ingredient to the database.
     *
     * @param ingredient  unmarshaled Ingredient instance from request body JSON.
     */
    @PostMapping("/ingredients/add")
    public void saveIngredient(@RequestBody Ingredient ingredient) {
        ingredientService.addIngredient(ingredient);
    }
}
