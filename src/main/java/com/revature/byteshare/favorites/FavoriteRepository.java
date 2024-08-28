package com.revature.byteshare.favorites;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    //For getting Git Commit To Actually Post to GitHub For Merge

    //@Query(value ="SELECT f from favorite f where f.account_associatedid=?1", nativeQuery = true)

    List<Favorite> findAllByUserUserId(int userId);

    //@Query(value ="SELECT f from favorite f where f.recipe_to_save=?1", nativeQuery = true)
    //List<favorite> findByRecipeID(int recipeID);
}
