package com.revature.byteshare.Tags;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {
    //TODO may need to be changes based on the 'recipe_id' name, test this
    // wanted to do this by using an inline query instead of an annotation query but well see
    Optional<List<Tag>> findAllByRecipeID(int id);

    //TODO need to test this, make sure query is spelled properly when all tables are made
    @Query("select t.recipe_Id from tags t where t.tag_name = :tag_name")
    Optional<List<Integer>> findAllRecipesByTags(@Param("tag_name") String tag_name);
}
