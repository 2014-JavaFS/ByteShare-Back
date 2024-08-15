package com.revature.byteshare.Tags;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name ="tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tag_id;

    @ManyToOne
    @JoinColumn(name = "recipe_Id") //TODO make sure this is the correct column string
    private int recipe_id;

    @Column(unique = true, nullable = false, columnDefinition = "varchar(12)")// 12 is entirely arbitrary lmk if it should change
    private String tag_name;
}
