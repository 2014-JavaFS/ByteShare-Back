package com.revature.byteshare.tags;

import com.revature.byteshare.recipe.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {

    @Query("select t.tag_name from Tag t where t.recipe = :recipe")
    Optional<List<String>> findAllTagNamesByRecipe(@Param("recipe") Recipe recipe);

    @Query("select t.recipe from Tag t where t.tag_name = :tag_name")
    Optional<List<Recipe>> findAllRecipesByTagNames(@Param("tag_name") String tag_name);
}
