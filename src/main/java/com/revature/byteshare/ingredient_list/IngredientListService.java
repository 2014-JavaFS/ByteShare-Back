package com.revature.byteshare.ingredient_list;

import com.revature.byteshare.ingredient_list.models.IngredientList;
import com.revature.byteshare.ingredient_list.models.IngredientListCreationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientListService {

    IngredientListRepository repository;

    @Autowired
    public IngredientListService(IngredientListRepository repository) {
        this.repository = repository;
    }

}
