package com.hdmstuttgart.fluffybear.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.hdmstuttgart.fluffybear.model.Recipe;
import org.springframework.data.repository.query.Param;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {  // why Long instead of long?

	Recipe findById(long id);
	List<Recipe> findByName(String name);
	@Query("SELECT r FROM Recipe r WHERE r.totalTime <= :maxTime AND r.totalTime > :minTime AND r.category IN :category " +
			"AND r.vegetarian = :vegetarian AND r.vegan = :vegan")
	List<Recipe> findByJsonParameters(@Param("minTime") int minTime,
									  @Param("maxTime") int maxTime,
									  @Param("category") List<String> category,
									  @Param("vegetarian") boolean vegetarian,
									  @Param("vegan") boolean vegan);

	@Query("SELECT r FROM Recipe r WHERE r.totalTime <= :maxTime AND r.totalTime > :minTime AND r.category IN :category ")
	List<Recipe> findByJsonParametersNoneVeganVegetarian(@Param("minTime") int minTime,
									  @Param("maxTime") int maxTime,
									  @Param("category") List<String> category);
}
