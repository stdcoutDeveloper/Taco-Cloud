package tacos.domain;

import lombok.Data;

// Generate getter, setter, constructor, equals, hashCode, toString automatically
@Data
public class Ingredient {

    private final String id;
    private final String name;
    private final Type type;

    public enum Type {
        WRAP,
        PROTEIN,
        VEGGIES,
        CHEESE,
        SAUCE
    }
    
}
