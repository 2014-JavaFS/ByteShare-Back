package com.revature.byteshare.ingredient.models;

import lombok.*;

/**
 * Ingredient represent an object retrieved from the Nutritionix API
 * from the /v2/search/instant or v2/natural/nutrients endpoints
 *
 * macros will be lazily loaded
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Ingredient {
    private String ingredientName;
    private int tagId;
    private String photoURL;
    private Macros macros;

    public Ingredient(String ingredientName, int tagId, String photoURL) {
        this.ingredientName = ingredientName;
        this.tagId = tagId;
        this.photoURL = photoURL;
        this.macros = null;
    }

    public Macros getMacros() {
        if(macros == null) {
            //TODO load macros
            macros = new Macros();
        }
        return macros;
    }
}