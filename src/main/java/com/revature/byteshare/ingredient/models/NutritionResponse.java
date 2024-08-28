package com.revature.byteshare.ingredient.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NutritionResponse {
    @JsonProperty("foods")
    private List<MacrosDTO> foods;
}
