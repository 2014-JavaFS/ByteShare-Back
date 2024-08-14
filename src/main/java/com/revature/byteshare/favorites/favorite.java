package com.revature.byteshare.favorites;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
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

    //No Args Constructor
    public favorite(){}

    public favorite(int accountAssociatedID, int recipeToSave){
        this.recipeToSave=recipeToSave;
        this.accountAssociatedID=accountAssociatedID;
    }
}
