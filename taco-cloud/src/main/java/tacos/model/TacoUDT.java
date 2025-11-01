package tacos.model;

import java.util.List;

import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import lombok.Data;

//UDT means user-defined type
@Data
@UserDefinedType("taco")
public class TacoUDT {

	private final String name;
	
	private final List<IngredientUDT> ingredients;
	
}
