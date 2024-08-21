package com.revature.byteshare.favorites;

import com.revature.byteshare.favorites.FavoriteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class favoriteRepositoryTesting {

    @Autowired
    private FavoriteRepository repository;


    @Autowired
    private TestEntityManager entityManager;



    @Test
    public void testSaving(){
        Favorite tempFavorite = new Favorite(1,2,3);
        Favorite toCheck = new Favorite();
        toCheck=repository.save(tempFavorite);
        assertNotNull(toCheck);
        assertEquals(toCheck.getFavoriteSerialID(), tempFavorite.getFavoriteSerialID());
        List<Favorite> getting = repository.findAll();
        toCheck=null;
        for(int i=0;i<getting.size();i++){
            if(getting.get(i).getAccountAssociatedID()==2 && getting.get(i).getRecipeToSave()==3)
                toCheck=getting.get(i);
        }
        assertNotNull(toCheck);
        assertEquals(toCheck.getFavoriteSerialID(), tempFavorite.getFavoriteSerialID());
    }

    @Test
    public void testDeleting(){

        assertEquals( 0,repository.count());
        Favorite tempFavorite = new Favorite(1,2,3);
        repository.save(tempFavorite);
        assertEquals( 1,repository.count());
        repository.delete(tempFavorite);
        assertEquals(0, repository.count());

    }
}
