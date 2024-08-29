package com.revature.byteshare.recipe_ingredient.models;

import com.revature.byteshare.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecipeAndIngredientList {

    private String title;
    private int recipeId;
    private User author;
    private Timestamp date;
    private String content; //either blob or text in SQL
    private int prepTime; // in minutes
    private int cookTime; // in minutes
    private List<RecipeIngredient> recipeIngredients;

}
