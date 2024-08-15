package com.revature.byteshare.ingredient_list.models;


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
    private String ingredientName;

    @ManyToOne
    private Recipe recipe;
    private double quantity;
    private String unit;

}
