package com.tutorial.hellospringtaco.converters;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.tutorial.hellospringtaco.model.Ingredient;
import com.tutorial.hellospringtaco.model.Ingredient.Type;

/**
 * Class used to convert what we POST with the form (ingredients text) to Ingredient class
 */
@Component
public class IngredientByIdConverter implements Converter<String,Ingredient>{
    private Map<String, Ingredient> resultMap = new HashMap<>();

    /**
     * without a db, this constructor populate the Ingredients in the map
     */
    public IngredientByIdConverter(){
        resultMap.put("AAA", new Ingredient("AAA", "καλαμαράκια", Type.VEGGIES));
        resultMap.put("BBB", new Ingredient("BBB", "φασόλια", Type.VEGGIES));
        resultMap.put("CCC", new Ingredient("CCC", "ψωμί", Type.WRAP));
        resultMap.put("DDD", new Ingredient("DDD", "πίτα", Type.WRAP));
        resultMap.put("EEE", new Ingredient("EEE", "μαγιονέζα", Type.SAUCE));
        resultMap.put("FFF", new Ingredient("FFF", "γιαούρτι", Type.SAUCE));
        resultMap.put("GGG", new Ingredient("GGG", "τζατζίκι", Type.SAUCE));
    }

    /**
     * the convert method take a String that is the ID and look up for the ingredient from the map
     *  */    
    @Override
    public Ingredient convert (String id){
        return resultMap.get(id);
    }
}