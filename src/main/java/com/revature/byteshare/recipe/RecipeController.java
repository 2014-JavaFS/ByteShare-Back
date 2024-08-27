package com.revature.byteshare.recipe;

import com.revature.byteshare.recipe_ingredient.RecipeIngredientService;
import com.revature.byteshare.recipe_ingredient.models.RecipeIngredient;
import com.revature.byteshare.recipe_ingredient.models.RecipeAndIngredientList;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    // Declare services required here
    private final RecipeService recipeService;
    private final RecipeIngredientService recipeIngredientService;

    @Autowired
    public RecipeController(RecipeService recipeService, RecipeIngredientService recipeIngredientService) {
        this.recipeService = recipeService;
        this.recipeIngredientService = recipeIngredientService;
    }
    @GetMapping
    public @ResponseBody List<Recipe> getAllRecipes(){
        return recipeService.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable int id){
        return ResponseEntity.ok(recipeService.findById(id));
    }
    @PutMapping
    public ResponseEntity<Boolean> putUpdateRecipe(@Valid @RequestBody Recipe updatedRecipe) {
        return ResponseEntity.ok(recipeService.update(updatedRecipe));
    }
    @GetMapping("/{userId}")
    public ResponseEntity<List<Recipe>> getAllOrdersByUserId(@PathVariable int userId){
        return ResponseEntity.ok(recipeService.findAllById(userId));
    }

    @GetMapping("/ingredientlist/{id}")
    public ResponseEntity<RecipeAndIngredientList> getRecipeWithIngredientList(@PathVariable int id){
        Recipe recipe = recipeService.findById(id);
        List<RecipeIngredient>recipeIngredients = recipeIngredientService.findAllIngredientsByRecipeId(id);
        RecipeAndIngredientList recipeAndIngredientList = RecipeAndIngredientList.builder()
                .recipeId(id)
                .author(recipe.getAuthor())
                .content(recipe.getContent())
                .cookTime(recipe.getCookTime())
                .date(recipe.getDate())
                .prepTime(recipe.getPrepTime())
                .recipeIngredients(recipeIngredients)
                .build();
        return ResponseEntity.ok(recipeAndIngredientList);

    }

}
