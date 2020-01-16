package com.hdmstuttgart.fluffybear.controller;


import com.hdmstuttgart.fluffybear.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class IngredientController {
    @Autowired
    IngredientService ingredientService;

    @RequestMapping("/ingredients")
    public List<String> getIngredients() {
        return ingredientService.getAllIngredients().stream().map(ingredient -> ingredient.getName()).collect(Collectors.toList());
    }
}
