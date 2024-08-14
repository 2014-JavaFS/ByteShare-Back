package com.revature.byteshare.ingredient_list.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class IngredientListCreationDto {

    int ingredientId;
    int recipeId;
    double quantity;
    String unit;
}
