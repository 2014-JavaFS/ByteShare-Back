package com.revature.byteshare.favorites;
import com.revature.byteshare.user.User;
import com.revature.byteshare.recipe.*;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
//JPA
@Entity
@Table(name = "favorite")
public class Favorite {
    //For getting Git Commit To Actually Post to GitHub For Merge


    //Declaring Variables
    @Id
    @SequenceGenerator(name = "NumberGenForFavorites", initialValue=1)
    @GeneratedValue(generator = "NumberGenForFavorites")
    private int favoriteSerialID;
    //TODO Check That These names and References are Correct
    @ManyToOne
    @JoinColumn(name = "accountAssociatedID", referencedColumnName = "userId", nullable = false)
    private User accountAssociatedID;
    //TODO Check That These names and References are Correct
    @ManyToOne
    @JoinColumn(name = "recipeToSave", referencedColumnName = "recipeId", nullable = false)
    private Recipe recipeToSave;


    public Favorite(User accountAssociatedID, Recipe recipeToSave){
        this.recipeToSave=recipeToSave;
        this.accountAssociatedID=accountAssociatedID;
    }
}