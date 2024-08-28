package com.revature.byteshare.favorites;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import com.revature.byteshare.favorites.dto.FavoriteResponseDTO;
import com.revature.byteshare.recipe.Recipe;
import com.revature.byteshare.user.User;
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
        //TODO: REWORK FOR DTO SWITCH
        Favorite temp = new Favorite();
        User tempUser = new User("Jacob@TestFavs.com","PasswordTest",
                "Testman","McTestserman","TestUserName",
                User.UserType.AUTHOR);
        tempUser.setUserId(1);

        Recipe tempRecipe = new Recipe();
        tempRecipe.setRecipeId(3);
        tempRecipe.setTitle("NULL POINTER FIX TEST");
        tempRecipe.setAuthor(tempUser);
        tempRecipe.setContent("");
        tempRecipe.setPrepTime(5);
        tempRecipe.setCookTime(5);
        tempRecipe.setDate(null);

        temp.setFavoriteSerialID(3);
        temp.setUser(tempUser);
        temp.setRecipeToSave(tempRecipe);

        Favorite temp2= new Favorite(1,tempUser,tempRecipe);

        List<FavoriteResponseDTO> toCheck = new ArrayList<>();
        FavoriteResponseDTO tempDTO = new FavoriteResponseDTO(temp.getUser(), temp.getRecipeToSave());
        toCheck.add(tempDTO);
        tempDTO = new FavoriteResponseDTO(temp2.getUser(), temp2.getRecipeToSave());
        toCheck.add(tempDTO);
        List<Favorite> forReturn = new ArrayList<>();
        forReturn.add(temp);
        forReturn.add(temp2);

        when(testingRepository.findAllByUserUserId(1)).thenReturn(forReturn);


        List<FavoriteResponseDTO> checking = testingService.findAllWithID(1);
        assertEquals(toCheck.get(1).getRecipeId(),checking.get(1).getRecipeId());

    }


    @Test
    public void testCreate(){
        User tempUser = new User("Jacob@TestFavs.com","PasswordTest",
                "Testman","McTestserman","TestUserName",
                User.UserType.AUTHOR);
        tempUser.setUserId(1);


        Recipe tempRecipe = new Recipe();
        tempRecipe.setRecipeId(3);
        tempRecipe.setTitle("NULL POINTER FIX TEST");
        tempRecipe.setAuthor(tempUser);
        tempRecipe.setContent("");
        tempRecipe.setPrepTime(5);
        tempRecipe.setCookTime(5);
        tempRecipe.setDate(null);

        Favorite temp2= new Favorite(1,tempUser,tempRecipe);
        //when(testingService.create(temp2)).thenReturn(temp2);
        when(testingRepository.save(temp2)).thenReturn(temp2);

        Favorite check = testingService.create(temp2);
        assertEquals(temp2.getFavoriteSerialID(), check.getFavoriteSerialID());
        assertEquals(temp2.getRecipeToSave(), check.getRecipeToSave());
        assertEquals(temp2.getRecipeToSave(), check.getRecipeToSave());
    }

    @Test
    public void testDelete(){

        User tempUser = new User("Jacob@TestFavs.com","PasswordTest",
                "Testman","McTestserman","TestUserName",
                User.UserType.AUTHOR);
        tempUser.setUserId(1);

        Recipe tempRecipe = new Recipe();
        tempRecipe.setRecipeId(3);
        tempRecipe.setTitle("NULL POINTER FIX TEST");
        tempRecipe.setAuthor(tempUser);
        tempRecipe.setContent("");
        tempRecipe.setPrepTime(5);
        tempRecipe.setCookTime(5);
        tempRecipe.setDate(null);

        Favorite temp2= new Favorite(1,tempUser,tempRecipe);
        //when(testingService.removeFavorite(1,3)).thenReturn(true);
        List<Favorite> forMocking = new ArrayList<>();
        forMocking.add(temp2);
        //doReturn(forMocking).when(testingRepository).findAll();
        when(testingRepository.findAllByUserUserId(1)).thenReturn(forMocking);

        boolean toCheck =false;

        toCheck=testingService.removeFavorite(1,3);
        assertTrue(toCheck);
        toCheck=testingService.removeFavorite(2,3);
        assertFalse(toCheck);
    }
}
