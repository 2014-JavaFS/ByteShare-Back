package com.revature.byteshare.recipe_ingredient.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.revature.byteshare.recipe.*;
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
public class RecipeIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String ingredientName;

    @ManyToOne
    @JsonBackReference
    private Recipe recipe;
    private double quantity;
    private String unit;

}
