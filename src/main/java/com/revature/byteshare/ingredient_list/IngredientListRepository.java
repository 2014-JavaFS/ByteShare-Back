package com.revature.byteshare.ingredient_list;

import com.revature.byteshare.ingredient_list.models.IngredientList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientListRepository extends JpaRepository<IngredientList, Integer> {
}
