package com.revature.byteshare.recipe_ingredient.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RecipeIngredientDto {

    String ingredient;
    int recipeId;
    double quantity;
    String unit;
}
