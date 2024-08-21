package com.revature.byteshare.ingredient;

import com.revature.byteshare.ingredient.models.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/{query}")
    public ResponseEntity<List<Ingredient>> searchFor(@PathVariable String query) {
        return ResponseEntity.ok(ingredientService.searchFor(query));
    }

    @GetMapping("/macros")
    public ResponseEntity<Macros> searchForMacros(@RequestParam String ingredientName) {
        return ResponseEntity.ok(ingredientService.getMacrosFor(ingredientName));
    }

    @PostMapping("/macros")
    public ResponseEntity<Ingredient> populateMacros(@RequestBody Ingredient ingredient) {
        return ResponseEntity.ok(ingredientService.populateMacros(ingredient));
    }
}
