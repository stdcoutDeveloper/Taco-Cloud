package tacos.repository;

import java.util.Optional;

import tacos.model.Ingredient;

public interface IngredientRepository {

	Iterable<Ingredient> findAll();

	Optional<Ingredient> findById(String id);

	// use for both insert and update
	// assure that the caller gets the latest version of object after saved
	Ingredient save(Ingredient ingredient);

}
