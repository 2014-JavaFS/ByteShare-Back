package com.revature.byteshare.ingredient.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IngredientDTO {
    @JsonProperty("food_name")
    private String foodName;
    @JsonProperty("tag_id")
    private int tagId;
    @JsonProperty("photo")
    private Photo photo;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class Photo{
        @JsonProperty("thumb")
        private String thumb;
    }

    public String getThumb(){
        return photo.getThumb();
    }
}
