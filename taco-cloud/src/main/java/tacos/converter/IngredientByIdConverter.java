package tacos.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import tacos.model.Ingredient;
import tacos.repository.IngredientRepository;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {
	
	/**
	 * ensure the variable is assigned only once and cannot be changed later
	 */
	private final IngredientRepository ingredientRepo;
	
	public IngredientByIdConverter(IngredientRepository ingredientRepo) {
		this.ingredientRepo = ingredientRepo;
	}

	/**
	 * Convert string received from the Form to corresponding Ingredient to Controller
	 */
	@Override
	public Ingredient convert(String id) {
		return ingredientRepo.findById(id).orElse(null);
	}
	
}
