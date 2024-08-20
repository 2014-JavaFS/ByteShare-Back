package com.revature.byteshare.ingredient;
import com.revature.byteshare.ingredient.models.Ingredient;
import com.revature.byteshare.ingredient.models.Macros;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class IngredientServiceUnitTestSuite {
    @Mock
    private IngredientRepository mockRepo;

    @InjectMocks
    private IngredientService ingredientService;

    private final String hamburger = "hamburger";
    private final Macros hamburgerMacros = new Macros(1.0, "sandwich", 226.0,
            540.14, 26.56, 34.28, 40.27, 0.0);
    private final Ingredient hamburgerIngredient = new Ingredient(hamburger, 608, "image:url");

    @BeforeEach
    public void setUp() {
        hamburgerIngredient.setMacros(null);
    }

    @Test
    public void testHamburgerSearch() throws Exception {
        String query = hamburgerIngredient.getIngredientName();
        when(mockRepo.searchFor(query)).thenReturn(List.of(hamburgerIngredient));

        List<Ingredient> result = ingredientService.searchFor(query);
        assertEquals(1, result.size());
        assertEquals(hamburgerIngredient, result.get(0));
    }

    @Test
    public void testPopulateMacros() throws Exception {

    }

    @Test
    public void testGetMacros() throws Exception {

    }

}
