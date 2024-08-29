package com.revature.byteshare.recipe_ingredient;

import com.revature.byteshare.recipe_ingredient.models.RecipeIngredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/recipeingredients")
public class RecipeIngredientController {
    RecipeIngredientService service;

    @Autowired
    public RecipeIngredientController(RecipeIngredientService service) {
        this.service = service;
    }
    @GetMapping("{id}")
    public ResponseEntity<List<RecipeIngredient>>getAllIngredients(@PathVariable int id){
        return ResponseEntity.ok(service.findAllIngredientsByRecipeId(id));
    }
}
