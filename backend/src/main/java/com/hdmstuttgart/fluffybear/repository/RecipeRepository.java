package com.hdmstuttgart.fluffybear.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.CrudRepository;
import com.hdmstuttgart.fluffybear.model.Recipe;

/**
 * Spring boot repository for Recipes which extends a CrudRepository
 */
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
	/**
	 * Returns a Recipe object from the connected database by a given id.
	 *
	 * @param id which is used for identifying the recipe in the database.
	 * @return the created Recipe object.
	 */
	Recipe findById(long id);

	/**
	 * Returns a list of recipes identified by the name.
	 *
	 * @param name which is used for identifying recipes.
	 * @return the list of recipes identified by the given name.
	 */
	List<Recipe> findByName(String name);

	/**
	 * Returns a list of filtered recipes. The filter values are defined by json parameters.
	 *
	 * @param minTime Minmal time of the recipe in minutes.
	 * @param maxTime Maximum time of the recipe in minutes.
	 * @param category List of all possible categories for the recipe.
	 * @param vegetarian indicating if the recipe is vegetarian.
	 * @param vegan indicating if the recipe is vegan.
	 * @return the filtered list of recipes
	 */
	@Query("SELECT r FROM Recipe r WHERE r.totalTime <= :maxTime AND r.totalTime > :minTime AND r.category IN :category " +
			"AND r.vegetarian = :vegetarian AND r.vegan = :vegan")
	List<Recipe> findByJsonParameters(@Param("minTime") int minTime,
									  @Param("maxTime") int maxTime,
									  @Param("category") List<String> category,
									  @Param("vegetarian") boolean vegetarian,
									  @Param("vegan") boolean vegan);

	/**
	 * Returns a list of all non vegan nor vegetarian filtered recipes. The filter values are defined by json parameters.
	 *
	 * @param minTime Minmal time of the recipe in minutes.
	 * @param maxTime Maximum time of the recipe in minutes.
	 * @param category List of all possible categories for the recipe.
	 * @return the filtered list of recipes
	 */
	@Query("SELECT r FROM Recipe r WHERE r.totalTime <= :maxTime AND r.totalTime > :minTime AND r.category IN :category ")
	List<Recipe> findByJsonParametersNoneVeganVegetarian(@Param("minTime") int minTime,
									  @Param("maxTime") int maxTime,
									  @Param("category") List<String> category);
}
