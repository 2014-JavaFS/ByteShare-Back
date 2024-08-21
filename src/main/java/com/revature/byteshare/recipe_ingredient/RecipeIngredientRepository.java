package com.revature.byteshare.recipe_ingredient;

import com.revature.byteshare.recipe_ingredient.models.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

    @Repository
    public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Integer> {

        public List<RecipeIngredient>findByRecipeId(int id);
    }
