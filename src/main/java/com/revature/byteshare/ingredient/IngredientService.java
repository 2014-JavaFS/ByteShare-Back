package com.revature.byteshare.ingredient;

import org.springframework.stereotype.Service;

@Service
public class IngredientService {
    public final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }
}
