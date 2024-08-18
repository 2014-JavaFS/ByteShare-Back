package com.revature.byteshare.ingredient;

import com.revature.byteshare.ingredient.models.Ingredient;
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
}
