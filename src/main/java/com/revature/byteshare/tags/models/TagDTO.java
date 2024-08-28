package com.revature.byteshare.tags.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagDTO {
    private Integer recipeId;
    private String tagName;
}
