package com.tutorial.hellospringtaco.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.tutorial.hellospringtaco.model.Ingredient;
import com.tutorial.hellospringtaco.model.Taco;
import com.tutorial.hellospringtaco.model.TacoOrder;
import com.tutorial.hellospringtaco.model.Ingredient.Type;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")         /*Maintains the taco order along the session */
public class MakeTacoController {

    /**
     * Create ingredients and add to the model [type - List(ingredients of that kind)]
     * @param model
     */
    @ModelAttribute
    public void addIngredientsToModel(Model model){
        List <Ingredient> ingredients = Arrays.asList(
            new Ingredient("AAA", "καλαμαράκια", Type.VEGGIES),
            new Ingredient("BBB", "φασόλια", Type.VEGGIES),            
            new Ingredient("CCC", "ψωμί", Type.WRAP),
            new Ingredient("DDD", "πίτα", Type.WRAP),
            new Ingredient("EEE", "μαγιονέζα", Type.SAUCE),
            new Ingredient("FFF", "γιαούρτι", Type.SAUCE),
            new Ingredient("GGG", "τζατζίκι", Type.SAUCE)
        );

        Type[] allTypes = Ingredient.Type.values();
        for (Type curr : allTypes){
            model.addAttribute(curr.toString().toLowerCase(), filterByType(ingredients, curr));
        }
    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order(){
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco(){
        return new Taco();
    }

    @GetMapping
    public String showDesignForm(){
        return "design";        
    }

    /**
     * Return (iterable) ingredients of a specific type
     * @param list
     * @param type
     * @return
     */
    private Iterable<Ingredient> filterByType(List<Ingredient> list, Type type){
        return list.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
    }

    /**
     * the sam as GetMapping, if used in combination with RequestMapping, 
     * this method handles the POST to the /design page
     * @param taco
     * @param order
     */
    @PostMapping
    public String processTaco(Taco taco, @ModelAttribute TacoOrder order){
        order.addTaco(taco);
        log.info("Processing Greek Taco {}", taco);
        return "redirect:/orders/current";
    }
}
