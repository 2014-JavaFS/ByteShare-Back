package com.revature.byteshare.favorites;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("Favorite")
public class favoriteController {

    //declarations
    private final favoriteService favoriteService;

    @Autowired
    favoriteController(favoriteService favoriteService){
        this.favoriteService=favoriteService;
    }

    @PostMapping
    public ResponseEntity<favorite> postAddToFavorites(@RequestHeader("userID") int userID,
                                             @RequestHeader("recipeID") int recipeID){
        //TODO ADD INPUT VALIDATION

        favorite makingFavorite = new favorite(userID, recipeID);

        return ResponseEntity.status(201).body(favoriteService.create(makingFavorite));
    }

    @GetMapping
    public List<favorite> getUsersFavorites(@RequestHeader int userID){
        //TODO ADD INPUT VALIDATION
        return favoriteService.findAllWithID(userID);
    }

    @DeleteMapping
    public void deleteFromFavorites(@RequestHeader("recipeID") int recipeID,
                                    @RequestHeader("userID") int userID){

        //TODO ADD INPUT VALIDATION

        /*To get to here input validation has already occurred so the recipe exists
        and the user exists therefore we call for the removal service.

        The delete call returns a boolean that is true if it deletes something
        and false if it fails to delete anything, so depending on what we get returned
        we can return to the user a response.
        * */
        boolean checking;
        checking=favoriteService.removeFavorite(userID, recipeID);
        if(checking){

            //TEMP to Hold Message
            System.out.println("Removed Recipe From Favorites");
        }
        else{
            //TODO: PUT LOGGING/RETURN TO USER MESSAGE HERE
            //TEMP to Hold Message
            System.out.println("User doesn't have that recipe favorited");
        }
    }



}
