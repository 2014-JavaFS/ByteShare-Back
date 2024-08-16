package com.revature.byteshare.recipe_ingredient;


import com.revature.byteshare.recipe.Recipe;
import com.revature.byteshare.recipe.RecipeRepository;
import com.revature.byteshare.recipe_ingredient.models.RecipeIngredient;
import com.revature.byteshare.recipe_ingredient.models.RecipeIngredientDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class RecipeIngredientServiceTest {
    @InjectMocks
    RecipeIngredientService recipeIngredientService;

    @Mock
    RecipeIngredientRepository recipeIngredientRepository;

    @Mock
    RecipeRepository recipeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateIngredient() {
        Recipe recipe = new Recipe();
        recipe.setId(1);

        RecipeIngredientDto dto = RecipeIngredientDto.builder()
                .ingredient("Sugar")
                .recipeId(1)
                .quantity(100.0)
                .unit("grams")
                .build();

        when(recipeRepository.findById(anyInt())).thenReturn(Optional.of(recipe));
        when(recipeIngredientRepository.save(any(RecipeIngredient.class))).thenAnswer(i -> i.getArguments()[0]);

        RecipeIngredient result = recipeIngredientService.createIngredient(dto);

        assertNotNull(result);
        assertEquals(dto.getIngredient(), result.getIngredientName());
        assertEquals(dto.getRecipeId(), result.getRecipe().getId());
        assertEquals(dto.getQuantity(), result.getQuantity());
        assertEquals(dto.getUnit(), result.getUnit());

        verify(recipeIngredientRepository, times(1)).save(result);
    }

    @Test
    void testUpdateIngredient() {
        Recipe recipe = new Recipe();
        recipe.setId(1);

        RecipeIngredient existingIngredient = RecipeIngredient.builder()
                .id(1)
                .ingredientName("Flour")
                .recipe(recipe)
                .quantity(200.0)
                .unit("grams")
                .build();

        RecipeIngredientDto dto = RecipeIngredientDto.builder()
                .ingredient("Sugar")
                .recipeId(1)
                .quantity(100.0)
                .unit("grams")
                .build();

        when(recipeIngredientRepository.findById(anyInt())).thenReturn(Optional.of(existingIngredient));
        when(recipeRepository.findById(anyInt())).thenReturn(Optional.of(recipe));
        when(recipeIngredientRepository.save(any(RecipeIngredient.class))).thenAnswer(i -> i.getArguments()[0]);

        RecipeIngredient result = recipeIngredientService.updateIngredient(dto, 1);

        assertNotNull(result);
        assertEquals(dto.getIngredient(), result.getIngredientName());
        assertEquals(dto.getRecipeId(), result.getRecipe().getId());
        assertEquals(dto.getQuantity(), result.getQuantity());
        assertEquals(dto.getUnit(), result.getUnit());

        verify(recipeIngredientRepository, times(1)).save(result);
    }

    @Test
    void testFindById() {
        RecipeIngredient ingredient = new RecipeIngredient();
        ingredient.setId(1);

        when(recipeIngredientRepository.findById(anyInt())).thenReturn(Optional.of(ingredient));

        RecipeIngredient result = recipeIngredientService.findById(1);

        assertNotNull(result);
        assertEquals(1, result.getId());

        verify(recipeIngredientRepository, times(1)).findById(1);
    }

    @Test
    void testFindAllIngredientsByRecipeId() {
        RecipeIngredient ingredient1 = new RecipeIngredient();
        ingredient1.setId(1);
        RecipeIngredient ingredient2 = new RecipeIngredient();
        ingredient2.setId(2);

        when(recipeIngredientRepository.findByRecipeId(anyInt())).thenReturn(Arrays.asList(ingredient1, ingredient2));

        List<RecipeIngredient> result = recipeIngredientService.findAllIngredientsByRecipeId(1);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals(2, result.get(1).getId());

        verify(recipeIngredientRepository, times(1)).findByRecipeId(1);
    }

}
