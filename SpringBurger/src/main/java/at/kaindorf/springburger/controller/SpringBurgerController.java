package at.kaindorf.springburger.controller;

import at.kaindorf.springburger.pojos.Burger;
import at.kaindorf.springburger.pojos.Ingredient;
import at.kaindorf.springburger.repo.BurgerRepository;
import at.kaindorf.springburger.repo.IngredientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequestMapping("/design")
@SessionAttributes("designBurger") // mehrere attribute mit { } einklammern
public class SpringBurgerController {

    private List<Ingredient> ingredients;
    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    private BurgerRepository burgerRepository;

    @ModelAttribute
    public void addAttributtes(Model model) {
        this.ingredients = ingredientRepository.findAll();
        Map<String, List<Ingredient>> ingredientsMap = new HashMap<>();

        for(Ingredient.Type type : Ingredient.Type.values()) {
            ingredientsMap.put(type.name().toLowerCase(), filterByType(type));
        }

        model.addAttribute("ingredients", ingredientsMap);
        model.addAttribute("designBurger", new Burger());
    }

    private List<Ingredient> filterByType(Ingredient.Type type) {
        return ingredients.stream()
                            .filter(i -> i.getType().equals(type))
                            .collect(Collectors.toList());
    }

    @GetMapping
    public String showDesign() {
        return "designForm";
    }

    @PostMapping
    public String processBurger(@Valid @ModelAttribute("designBurger") Burger burger, Errors errors) {
        log.info(burger.toString());
        if (errors.hasErrors()){
            log.info(errors.getObjectName() + " " + errors.getAllErrors());
            return "designForm";
        }
        burgerRepository.save(burger);
        return "redirect:/orders/current";
    }

}
