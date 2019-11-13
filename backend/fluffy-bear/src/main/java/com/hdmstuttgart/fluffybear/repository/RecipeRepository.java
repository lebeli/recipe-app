package com.hdmstuttgart.fluffybear.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.hdmstuttgart.fluffybear.model.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

	Recipe findById(long id);
	List<Recipe> findByName(String name);
}
