package com.revature.byteshare.recipe_ingredient;

import com.revature.byteshare.recipe_ingredient.models.RecipeIngredient;
import com.revature.byteshare.recipe_ingredient.models.RecipeIngredientDto;
import com.revature.byteshare.recipe.RecipeRepository;
import com.revature.byteshare.util.exceptions.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeIngredientService {

    RecipeIngredientRepository repository;
    RecipeRepository recipeRepository;

    @Autowired
    public RecipeIngredientService(RecipeIngredientRepository repository, RecipeRepository recipeRepository) {
        this.repository = repository;
        this.recipeRepository = recipeRepository;
    }

    public RecipeIngredient createIngredient(RecipeIngredientDto recipeIngredientDto){
        RecipeIngredient recipeIngredient = RecipeIngredient.builder()
                .recipe(recipeRepository.findById(recipeIngredientDto.getRecipeId()).orElseThrow(()-> new DataNotFoundException("recipe id not found")))
                .ingredientName(recipeIngredientDto.getIngredient())
                .quantity(recipeIngredientDto.getQuantity())
                .unit(recipeIngredientDto.getUnit())
                .build();

        repository.save(recipeIngredient);
        return recipeIngredient;
    }

    public RecipeIngredient updateIngredient(RecipeIngredientDto recipeIngredientDto, int id){
        RecipeIngredient recipeIngredient = repository.findById(id).orElseThrow(()->new DataNotFoundException("Ingredient not found at this id"));
        recipeIngredient.setIngredientName(recipeIngredientDto.getIngredient());
        recipeIngredient.setRecipe(recipeRepository.findById(recipeIngredientDto.getRecipeId()).
                orElseThrow(()-> new DataNotFoundException("recipe id not found")));
        recipeIngredient.setQuantity(recipeIngredientDto.getQuantity());
        recipeIngredient.setUnit(recipeIngredientDto.getUnit());
        return repository.save(recipeIngredient);
    }

    public RecipeIngredient findById(int id){
        return repository.findById(id).orElseThrow(() -> new DataNotFoundException("Ingredient not found at this id"));
    }
    public List<RecipeIngredient>findAllIngredientsByRecipeId(int recipeId){
        return repository.findByRecipeId(recipeId);
    }
    public void delete(int id){
        repository.deleteById(id);
    }

}
