package com.revature.byteshare.ingredient.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Macros {
    private int servingQuantity;
    private String servingUnit;
    private double gramsPerServing;
    private double calories;
    //below quantities are all in grams
    private double fat;
    private double protein;

    private double totalCarbs;
    private double sugars;
    private double fiber;
}
