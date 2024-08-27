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
    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable int id){
        return ResponseEntity.ok(recipeService.findById(id));
    }

    @GetMapping("/{title}")
    public ResponseEntity<Recipe> getRecipeByTitle(@PathVariable String title){
        return ResponseEntity.ok(recipeService.findByTitle(title));
    }

    @GetMapping("/{authorId}")
    public ResponseEntity<Recipe> getRecipeByAuthorId(@PathVariable int authorId){
        return ResponseEntity.ok(recipeService.findByAuthorId(authorId));
    }

    @PutMapping
    public ResponseEntity<Boolean> putUpdateRecipe(@Valid @RequestBody Recipe updatedRecipe) {
        return ResponseEntity.ok(recipeService.update(updatedRecipe));
    }
    @GetMapping("/{userId}")
    public ResponseEntity<List<Recipe>> getAllOrdersByUserId(@PathVariable int userId){
        return ResponseEntity.ok(recipeService.findAllById(userId));
    }
    @PostMapping
    public ResponseEntity<Recipe> postCreateRecipe(@Valid @RequestBody Recipe recipe){
        return ResponseEntity.status(201).body(recipeService.create(recipe));
    }
}
