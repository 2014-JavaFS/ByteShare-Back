package com.revature.byteshare.ingredient;

import com.revature.byteshare.ingredient.models.*;
import com.revature.byteshare.util.exceptions.NutritionixException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {
    public final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public List<Ingredient> searchFor(String query) throws NutritionixException {
        return ingredientRepository.searchFor(query);
    }

    public Ingredient populateMacros(Ingredient ingredient) throws NutritionixException {
        if(ingredient == null){
            throw new NullPointerException("Ingredient is null");
        }
        ingredient.setMacros(ingredientRepository.getMacrosFor(ingredient.getIngredientName()));
        return ingredient;
    }

    public Macros getMacrosFor(String ingredientName) throws NutritionixException {
        return ingredientRepository.getMacrosFor(ingredientName);
    }
}
