package com.revature.byteshare.favorites;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;

    @Autowired
    FavoriteService(FavoriteRepository favoriteRepository){
        this.favoriteRepository=favoriteRepository;
    }

    public Favorite create(Favorite favorite){
        return favoriteRepository.save(favorite);
    }


    public List<Favorite> findByRecipeID(int recipeID){
        //return favoriteRepository.findByRecipeID(recipeID);

        return null;
    }

    public List<Favorite> findAll(){
        return favoriteRepository.findAll();
    }

    public List<Favorite> findAllWithID(int userID){

        List<Favorite> temp =findAll();
        List<Favorite> buisnessLogicList = new ArrayList<>();
        for(int i=0; i<temp.size();i++){
            if(temp.get(i).getAccountAssociatedID() == userID) {
                buisnessLogicList.add(temp.get(i));
            }
        }
        //buisnessLogicList=favoriteRepository.findByUserID(userID);
        return buisnessLogicList;
    }

    public boolean removeFavorite(int userID, int recipeID){

        List<Favorite> temp =favoriteRepository.findAll();
        for(int i=0; i<temp.size();i++){
            if(temp.get(i).getRecipeToSave() == recipeID) {
                favoriteRepository.delete(temp.get(i));
                return true;
                }
        }

        return false;
    }
}
