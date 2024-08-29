package com.revature.byteshare.ingredient.models;

import lombok.*;

import java.util.Collection;

/**
 * Macros class is a component of Ingredient and represents the macronutrients available per serving size
 * as well as the serving size information
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Macros {
    private double servingQuantity;
    private String servingUnit;
    private double gramsPerServing;
    private double calories;  //in kCal
    //below quantities are all in grams
    private double fat;
    private double protein;

    private double totalCarbs;
    private double sugars;
}
