package com.revature.byteshare.favorites;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
//JPA
@Entity
@Table(name = "favorite")
public class favorite {


    //Declaring Variables
    @Id
    @SequenceGenerator(name = "NumberGenForFavorites", initialValue=1)
    @GeneratedValue(generator = "NumberGenForFavorites")
    private int favoriteSerialID;
    //TODO Insert Annotations here
    private int accountAssociatedID;
    //TODO Insert Annotations here
    private int recipeToSave;


    public favorite(int accountAssociatedID, int recipeToSave){
        this.recipeToSave=recipeToSave;
        this.accountAssociatedID=accountAssociatedID;
    }
}
