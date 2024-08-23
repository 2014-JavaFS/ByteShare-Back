package com.revature.byteshare.ingredient.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MacrosDTO {
    @JsonProperty("serving_qty")
    private double servingQty;
    @JsonProperty("serving_unit")
    private String servingUnit;
    @JsonProperty("serving_weight_grams")
    private double gramsPerServing;
    @JsonProperty("nf_calories")
    private double calories;
    @JsonProperty("nf_total_fat")
    private double fat;
    @JsonProperty("nf_protein")
    private double protein;
    @JsonProperty("nf_total_carbohydrate")
    private double totalCarbs;
    @JsonProperty("nf_sugars")
    private double sugars;
}
