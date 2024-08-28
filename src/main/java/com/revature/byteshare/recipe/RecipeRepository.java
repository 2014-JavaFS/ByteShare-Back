package com.revature.byteshare.recipe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
    List<Recipe> findAllByAuthorUserId(int userId);

    @Query("select r from Recipe r where r.content like %?1% or r.title like %?1% or r.author.username like %?1%")
    List<Recipe> searchFor(String query);
}

