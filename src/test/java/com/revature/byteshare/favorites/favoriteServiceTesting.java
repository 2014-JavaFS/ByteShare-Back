package com.revature.byteshare.favorites;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class favoriteServiceTesting {

    @Mock
    private favoriteRepository testingRepository;

    @InjectMocks
    private favoriteService testingService;

    @Test
    public void testGetByUserID(){
        favorite temp = new favorite();
        temp.setFavoriteSerialID(3);
        temp.setAccountAssociatedID(1);
        temp.setRecipeToSave(1);
        favorite temp2= new favorite(1,1,3);

        List<favorite> toCheck = new ArrayList<>();
        toCheck.add(temp);
        toCheck.add(temp2);

        when(testingService.findAllWithID(1)).thenReturn(toCheck);

        List<favorite> checking = testingService.findAllWithID(1);
        assertEquals(toCheck.get(1).getFavoriteSerialID(),checking.get(1).getFavoriteSerialID());

    }

    @Test
    public void testGetByRecipeID(){

    }

    @Test
    public void testCreate(){
        favorite temp2= new favorite(1,1,3);
        when(testingService.create(temp2)).thenReturn(temp2);

        favorite check = testingService.create(temp2);
        assertEquals(temp2.getFavoriteSerialID(), check.getFavoriteSerialID());
        assertEquals(temp2.getRecipeToSave(), check.getRecipeToSave());
        assertEquals(temp2.getRecipeToSave(), check.getRecipeToSave());
    }

    @Test
    public void testDelete(){
        favorite temp2= new favorite(1,1,3);
        //TODO Figure out how to Mock this to actually work
        //when(testingService.removeFavorite(1,3)).thenReturn(true);
        boolean toCheck =false;

        toCheck=testingService.removeFavorite(1,3);
        assertTrue(toCheck);
        toCheck=testingService.removeFavorite(2,3);
        assertFalse(toCheck);
    }
}
