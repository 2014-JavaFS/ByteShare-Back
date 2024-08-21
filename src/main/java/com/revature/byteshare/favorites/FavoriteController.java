package com.revature.byteshare.favorites;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/favorite")
public class FavoriteController {
    //For getting Git Commit To Actually Post to GitHub For Merge

    //declarations
    private final FavoriteService favoriteService;

    @Autowired
    FavoriteController(FavoriteService favoriteService){
        this.favoriteService=favoriteService;
    }

    @PostMapping
    public ResponseEntity<Favorite> postAddToFavorites(@RequestHeader("userID") int userID,
                                             @RequestHeader("recipeID") int recipeID){
        if(userID <=0 || recipeID <=0){
            return ResponseEntity.status(400).body(null);
        }

        Favorite makingFavorite = new Favorite(userID, recipeID);

        return ResponseEntity.status(201).body(favoriteService.create(makingFavorite));
    }

    @GetMapping
    public ResponseEntity<List<Favorite>> getUsersFavorites(@RequestHeader int userID){

        if(userID <=0 ){
            return ResponseEntity.status(400).body(null);
        }

        return ResponseEntity.status(200).body(favoriteService.findAllWithID(userID));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteFromFavorites(@RequestHeader("recipeID") int recipeID,
                                    @RequestHeader("userID") int userID){

        if(userID <=0 || recipeID <=0){
            return ResponseEntity.status(400).body(null);
        }

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
            //Testing Return
            return ResponseEntity.status(200).body("Removed from Favorites");
        }
        else{
            //TODO: PUT LOGGING/RETURN TO USER MESSAGE HERE
            //TEMP to Hold Message
            System.out.println("User doesn't have that recipe favorited");
            //Testing return
            return ResponseEntity.status(200).body("Failed to remove from Favorites");
        }
    }



}
