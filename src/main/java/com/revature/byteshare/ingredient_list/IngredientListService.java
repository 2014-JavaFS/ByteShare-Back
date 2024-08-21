package com.revature.byteshare.ingredient_list;

import com.revature.byteshare.ingredient.models.Ingredient;
import com.revature.byteshare.ingredient_list.models.IngredientList;
import com.revature.byteshare.ingredient_list.models.IngredientListCreationDto;
import com.revature.byteshare.recipe.RecipeRepository;
import com.revature.byteshare.util.exceptions.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientListService {

    IngredientListRepository repository;
    RecipeRepository recipeRepository;

    @Autowired
    public IngredientListService(IngredientListRepository repository) {
        this.repository = repository;
    }


    public IngredientList createIngredient(IngredientListCreationDto ingredientListCreationDto){
        IngredientList ingredientList = IngredientList.builder()
                .recipe(recipeRepository.findById(ingredientListCreationDto.getRecipeId()).orElseThrow(()-> new DataNotFoundException("recipe id not found")))
                .ingredientName(ingredientListCreationDto.getIngredient())
                .quantity(ingredientListCreationDto.getQuantity())
                .unit(ingredientListCreationDto.getUnit())
                .build();

        repository.save(ingredientList);
        return ingredientList;
    }

    public IngredientList updateIngredient(IngredientListCreationDto ingredientListCreationDto, int id){
        IngredientList ingredientList = repository.findById(id).orElseThrow(()->new DataNotFoundException("Ingredient not found at this id"));
        ingredientList.setIngredientName(ingredientListCreationDto.getIngredient());
        ingredientList.setRecipe(recipeRepository.findById(ingredientListCreationDto.getRecipeId()).
                orElseThrow(()-> new DataNotFoundException("recipe id not found")));
        ingredientList.setQuantity(ingredientList.getQuantity());
        ingredientList.setUnit(ingredientList.getUnit());
        repository.save(ingredientList);
        return ingredientList;
    }

    public IngredientList findById(int id){
        return repository.findById(id).orElseThrow(() -> new DataNotFoundException("Ingredient not found at this id"));
    }
    public List<IngredientList>findAllIngredientsByRecipeId(int recipeId){
        return repository.findByRecipeId(recipeId);
    }
    public void delete(int id){
        repository.deleteById(id);
    }

}
