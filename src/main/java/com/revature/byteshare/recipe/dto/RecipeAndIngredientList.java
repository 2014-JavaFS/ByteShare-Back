package com.revature.byteshare.recipe.dto;

import com.revature.byteshare.recipe_ingredient.models.RecipeIngredient;
import com.revature.byteshare.user.User;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecipeAndIngredientList {

    private int recipeId;
    private User author;
    private Timestamp date;
    private String content; //either blob or text in SQL
    private int prepTime; // in minutes
    private int cookTime; // in minutes
    private String title;
    private List<RecipeIngredient> recipeIngredients;

}
