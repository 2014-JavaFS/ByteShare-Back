package com.revature.byteshare.tags.models;

import com.revature.byteshare.recipe.Recipe;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
//@Table(name ="Tag") dont actually need this
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tag_id;

    @ManyToOne
    @JoinColumn(name ="recipe")
    private Recipe recipe;

    @Column(nullable = false, columnDefinition = "varchar(12)")// 12 is entirely arbitrary lmk if it should change
    private String tag_name;

}
