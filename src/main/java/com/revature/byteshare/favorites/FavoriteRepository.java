package com.revature.byteshare.favorites;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {

    //@Query(value ="SELECT f from favorite f where f.account_associatedid=?1", nativeQuery = true)
    //List<favorite> findByUserID(int userID);
    //@Query(value ="SELECT f from favorite f where f.recipe_to_save=?1", nativeQuery = true)
    //List<favorite> findByRecipeID(int recipeID);
}
