package tacos.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import tacos.util.TacoUDRUtils;

@Data
@Table("tacos")
public class Taco {
	
	@PrimaryKey
	private TacoKey key = new TacoKey();

	@NotNull
	@Size(min=5, message="Name must be at least 5 characters long")
	private String name;

	@NotNull
	@Size(min=1, message="You must choose at least 1 ingredient")
	@Column("ingredients")
	private List<IngredientUDT> ingredients = new ArrayList<>();
	
	public void addIngredient(Ingredient ingredient) {
		this.ingredients.add(TacoUDRUtils.toIngredientUDT(ingredient));
	}
	
}
