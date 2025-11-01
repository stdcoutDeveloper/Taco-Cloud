package tacos.util;

import tacos.model.Ingredient;
import tacos.model.IngredientUDT;

public class TacoUDRUtils {

	public static IngredientUDT toIngredientUDT(Ingredient ingredient) {
		return new IngredientUDT(ingredient.getName(), ingredient.getType());
	}

}
