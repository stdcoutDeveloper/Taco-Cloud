package tacos.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import tacos.model.Ingredient;
import tacos.model.Ingredient.Type;
import tacos.model.Taco;
import tacos.model.TacoOrder;
import tacos.repository.IngredientRepository;

@Slf4j
@Controller
@RequestMapping("/design")
// "tacoOrder" should be maintained in session so that it can span multiple requests
@SessionAttributes("tacoOrder")
public class DesignTacoController {
	
	@Autowired
	private IngredientRepository ingredientRepository;
	
	/**
	 * Populate the Model before handling request (before Controller is called)
	 * @param model
	 */
	@ModelAttribute
	public void addIngredientsToModel(Model model) {
		List<Ingredient> ingredients = StreamSupport.stream(ingredientRepository.findAll().spliterator(), false)
                									.collect(Collectors.toList());
		
		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
		}
	}
	
	/**
	 * Initialize "tacoOrder" object in session
	 * @return
	 */
	@ModelAttribute("tacoOrder")
	public TacoOrder order() {
		return new TacoOrder();
	}

	/**
	 * Bind to "taco" object on the form
	 * <form method="POST" th:object="${taco}">
	 * The form has a non-null object to display (have a blank slate)
	 * @return
	 */
	@ModelAttribute("taco")
	public Taco taco() {
		return new Taco();
	}

	@GetMapping
	public String showDesignForm() {
		return "design";
	}
	
	@PostMapping
	public String processTaco(@Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder) {
		if (errors.hasErrors()) {
			return "design";
		}

	    tacoOrder.addTaco(taco);
	    log.info("Processing taco: {}", taco);

	    return "redirect:/orders/current";
	}

	private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
		return ingredients
			    .stream()
			    .filter(x -> x.getType().equals(type))
			    .collect(Collectors.toList());
	}
	
}
