package com.revature.byteshare.favorites;

import com.revature.byteshare.recipe.Recipe;
import com.revature.byteshare.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class FavoriteRepositoryTesting {

    @Autowired
    private FavoriteRepository repository;


    @Autowired
    private TestEntityManager entityManager;

    //For getting Git Commit To Actually Post to GitHub For Merge


    @Test
    public void testSaving(){

        User tempUser = new User("Jacob@TestFavs.com","PasswordTest",
                "Testman","McTestserman","TestUserName",
                User.UserType.AUTHOR);

        Recipe tempRecipe = new Recipe();
        tempRecipe.setRecipeId(3);

        Favorite tempFavorite = new Favorite(1,tempUser,tempRecipe);
        Favorite toCheck;
        toCheck=repository.save(tempFavorite);
        assertNotNull(toCheck);
        assertEquals(toCheck.getFavoriteSerialID(), tempFavorite.getFavoriteSerialID());
        List<Favorite> getting = repository.findAll();
        toCheck=null;
        for(int i=0;i<getting.size();i++){
            if(getting.get(i).getAccountAssociatedID().getUserId()==2 && getting.get(i).getRecipeToSave().getRecipeId()==3)
                toCheck=getting.get(i);
        }
        assertNotNull(toCheck);
        assertEquals(toCheck.getFavoriteSerialID(), tempFavorite.getFavoriteSerialID());
    }

    @Test
    public void testDeleting(){

        User tempUser = new User("Jacob@TestFavs.com","PasswordTest",
                "Testman","McTestserman","TestUserName",
                User.UserType.AUTHOR);

        Recipe tempRecipe = new Recipe();
        tempRecipe.setRecipeId(3);

        assertEquals( 0,repository.count());
        Favorite tempFavorite = new Favorite(1,tempUser,tempRecipe);
        repository.save(tempFavorite);
        assertEquals( 1,repository.count());
        repository.delete(tempFavorite);
        assertEquals(0, repository.count());

    }
}
