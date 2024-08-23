package com.revature.byteshare.tags.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagDTO {
    private Integer recipe_id;
    private String tag_name;
}
