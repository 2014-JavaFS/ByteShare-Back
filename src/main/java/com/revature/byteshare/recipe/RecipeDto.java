package com.revature.byteshare.recipe;

import com.revature.byteshare.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
@Data
@AllArgsConstructor
public class RecipeDto {
    private int author;
    private String title;
    private String content; //either blob or text in SQL
    private int prepTime; // in minutes
    private int cookTime; // in minutes
}
