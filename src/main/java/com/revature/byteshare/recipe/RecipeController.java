package com.revature.byteshare.recipe;

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

    @Autowired

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public @ResponseBody List<Recipe> getAllRecipes(){
        return recipeService.findAll();
    }

    @PostMapping
    public ResponseEntity<Recipe> postRecipe(@Valid @RequestBody Recipe newRecipe){
        return ResponseEntity.ok(recipeService.update(newRecipe));
    }
}
