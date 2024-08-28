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
@CrossOrigin
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
    @GetMapping("/author/{userId}")
    public ResponseEntity<List<Recipe>> getAllOrdersByUserId(@PathVariable int userId){
        return ResponseEntity.ok(recipeService.findAllById(userId));
    }
    @PostMapping
    public ResponseEntity<Recipe> postRecipe(@RequestBody RecipeDto recipeDto){
        return ResponseEntity.status(201).body(recipeService.create(recipeDto));
    }
    @GetMapping("/search")
    public ResponseEntity<List<Recipe>> searchFor(@RequestParam String query){
        return ResponseEntity.status(200).body(recipeService.searchFor(query));
    }
    @GetMapping("/ingredientlist/{id}")
    public ResponseEntity<RecipeAndIngredientList> getRecipeWithIngredientList(@PathVariable int id){
        Recipe recipe = recipeService.findById(id);
        List<RecipeIngredient>recipeIngredients = recipeIngredientService.findAllIngredientsByRecipeId(id);
        RecipeAndIngredientList recipeAndIngredientList = RecipeAndIngredientList.builder()
                .recipeId(id)
                .author(recipe.getAuthor())
                .title(recipe.getTitle())
                .content(recipe.getContent())
                .cookTime(recipe.getCookTime())
                .date(recipe.getDate())
                .prepTime(recipe.getPrepTime())
                .recipeIngredients(recipeIngredients)
                .build();
        return ResponseEntity.ok(recipeAndIngredientList);

    }

}
