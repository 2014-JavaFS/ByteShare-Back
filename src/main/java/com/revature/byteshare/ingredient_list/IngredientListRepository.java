package com.revature.byteshare.ingredient_list;

import com.revature.byteshare.ingredient_list.models.IngredientList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientListRepository extends JpaRepository<IngredientList, Integer> {

    public List<IngredientList>findByRecipeId(int id);
}
