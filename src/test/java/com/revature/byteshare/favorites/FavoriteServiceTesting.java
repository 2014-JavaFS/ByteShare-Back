package com.revature.byteshare.favorites;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class FavoriteServiceTesting {

    //For getting Git Commit To Actually Post to GitHub For Merge
    @Mock
    private FavoriteRepository testingRepository;

    @InjectMocks
    private FavoriteService testingService;

    @Test
    public void testGetByUserID(){
        Favorite temp = new Favorite();
        temp.setFavoriteSerialID(3);
        temp.setAccountAssociatedID(1);
        temp.setRecipeToSave(1);
        Favorite temp2= new Favorite(1,1,3);

        List<Favorite> toCheck = new ArrayList<>();
        toCheck.add(temp);
        toCheck.add(temp2);

        when(testingService.findAllWithID(1)).thenReturn(toCheck);

        List<Favorite> checking = testingService.findAllWithID(1);
        assertEquals(toCheck.get(1).getFavoriteSerialID(),checking.get(1).getFavoriteSerialID());

    }


    @Test
    public void testCreate(){
        Favorite temp2= new Favorite(1,1,3);
        when(testingService.create(temp2)).thenReturn(temp2);

        Favorite check = testingService.create(temp2);
        assertEquals(temp2.getFavoriteSerialID(), check.getFavoriteSerialID());
        assertEquals(temp2.getRecipeToSave(), check.getRecipeToSave());
        assertEquals(temp2.getRecipeToSave(), check.getRecipeToSave());
    }

    @Test
    public void testDelete(){
        //@TODO Implement whenever/If we switch from back end filtering to custom Query filtering
        Favorite temp2= new Favorite(1,1,3);
        //when(testingService.removeFavorite(1,3)).thenReturn(true);
        List<Favorite> forMocking = new ArrayList<>();
        doReturn(forMocking).when(testingRepository).findAll();

        boolean toCheck =false;

        toCheck=testingService.removeFavorite(1,3);
        assertTrue(toCheck);
        toCheck=testingService.removeFavorite(2,3);
        assertFalse(toCheck);
    }
}
