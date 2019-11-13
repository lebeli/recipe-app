package com.hdmstuttgart.fluffybear.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hdmstuttgart.fluffybear.model.Recipe;

@RestController
public class RecipeController {
	
	@RequestMapping("/recipe/{id}")
    public Recipe getRecipe(@PathVariable("id") Long id) {
		// TODO: implement
		return null;
    }
}
