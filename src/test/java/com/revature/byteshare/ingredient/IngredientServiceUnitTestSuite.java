package com.revature.byteshare.ingredient;
import com.revature.byteshare.ingredient.models.Ingredient;
import com.revature.byteshare.ingredient.models.Macros;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
        when(mockRepo.searchFor(hamburger)).thenReturn(List.of(hamburgerIngredient));

        List<Ingredient> result = ingredientService.searchFor(hamburger);
        assertEquals(1, result.size());
        assertEquals(hamburgerIngredient, result.get(0));
    }

    @Test
    public void testPopulateMacros() throws Exception {
        when(mockRepo.getMacrosFor(hamburger)).thenReturn(hamburgerMacros);

        Ingredient result = ingredientService.populateMacros(hamburgerIngredient);
        assertEquals(result, hamburgerIngredient);
        assertEquals(hamburgerMacros, hamburgerIngredient.getMacros());
    }

    @Test
    public void testGetMacros() throws Exception {
        when(mockRepo.getMacrosFor(hamburger)).thenReturn(hamburgerMacros);

        Macros result = ingredientService.getMacrosFor(hamburger);
        assertNotNull(result);
        assertEquals(hamburgerMacros, result);
    }

    @Test
    public void testPopulateMacrosThrowsNullPointerException() throws Exception {
        assertThrows(NullPointerException.class, () -> ingredientService.populateMacros(null));
    }

}
