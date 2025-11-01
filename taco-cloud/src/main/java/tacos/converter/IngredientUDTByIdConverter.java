package tacos.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import tacos.model.Ingredient;
import tacos.model.IngredientUDT;
import tacos.repository.IngredientRepository;

@Component
public class IngredientUDTByIdConverter implements Converter<String, IngredientUDT> {
	
	/**
	 * ensure the variable is assigned only once and cannot be changed later
	 */
	private final IngredientRepository ingredientRepo;
	
	public IngredientUDTByIdConverter(IngredientRepository ingredientRepo) {
		this.ingredientRepo = ingredientRepo;
	}

	/**
	 * Convert string received from the Form to corresponding Ingredient to Controller
	 */
	@Override
	public IngredientUDT convert(String id) {
		Ingredient ingredient = ingredientRepo.findById(id).orElse(null);
		return ingredient != null ?
			   new IngredientUDT(ingredient.getName(), ingredient.getType()) :
			   null;
	}
	
}
