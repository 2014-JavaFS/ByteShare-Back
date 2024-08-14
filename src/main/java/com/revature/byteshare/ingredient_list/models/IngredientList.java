package com.revature.byteshare.ingredient_list.models;


import com.revature.byteshare.ingredient.Ingredient;
import com.revature.byteshare.recipe.Recipe;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IngredientList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Ingredient ingredient;
    @ManyToOne
    private Recipe recipe;
    private double quantity;
    private String unit;

}
