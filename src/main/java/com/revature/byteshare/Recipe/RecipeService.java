package com.revature.byteshare.Recipe;


import com.revature.byteshare.util.exceptions.DataNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.revature.byteshare.util.interfaces.Serviceable;

import java.util.List;

@Service
public class RecipeService implements Serviceable<Recipe> {
    private RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public List<Recipe> findAll() {
        List<Recipe> recipes = recipeRepository.findAll();
        if (recipes.isEmpty()){
            throw new DataNotFoundException("No recipe information available");
        } else {
            return recipes;
        }
    }

    @Override
    public Recipe create(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @Override
    public Recipe findById(int recipeNumber) {
        Recipe foundRecipe = recipeRepository.findById(recipeNumber)
                .orElseThrow(() -> new DataNotFoundException("No recipe with the ID of " + recipeNumber));
        return foundRecipe;
    }

    @Transactional
    @Override
    public boolean update(Recipe recipeToUpdate) {
        Recipe foundRecipe = recipeRepository.findById(recipeToUpdate.getRecipeId())
                .orElseThrow(() -> new DataNotFoundException("No recipe with the ID of " + recipeToUpdate.getRecipeId()));
        if (foundRecipe == null) {
            throw new DataNotFoundException("Order with that ID is not in the database, please check again");
        }
        return true;
    }

    @Override
    public boolean delete(Recipe recipe) {
        recipeRepository.delete(recipe);
        return true;
    }

    public List<Recipe> findAllById(int userId) {
        List<Recipe> recipes = recipeRepository.findAllByUserUserId(userId);
        if (recipes.isEmpty()){
            throw new DataNotFoundException("No recipes with that userId was found");
        } else {
            return recipes;
        }
    }
}
