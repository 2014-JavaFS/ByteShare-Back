package com.revature.byteshare.ingredient;

import com.revature.byteshare.ingredient.models.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class IngredientService {
    public final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public List<Ingredient> searchFor(String query) throws IOException, InterruptedException {
        return ingredientRepository.searchFor(query);
    }

    public Ingredient populateMacros(Ingredient ingredient) throws IOException, InterruptedException {
        if(ingredient == null){
            throw new NullPointerException("Ingredient is null");
        }
        if(ingredient.getMacros() != null){
            throw new IllegalArgumentException("Macros are already populated");
        }
        ingredient.setMacros(ingredientRepository.getMacrosFor(ingredient.getIngredientName()));
        return ingredient;
    }

    public Macros getMacrosFor(String ingredientName) throws IOException, InterruptedException {
        return ingredientRepository.getMacrosFor(ingredientName);
    }
}
