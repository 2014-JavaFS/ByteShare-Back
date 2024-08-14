package com.revature.byteshare.favorites;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface favoriteRepository extends JpaRepository<favorite, Integer> {
    List<favorite> findByUserID(int userID);
    List<favorite> findByRecipeID(int recipeID);
}
