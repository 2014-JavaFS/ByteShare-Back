package com.revature.byteshare.ingredient;

import com.revature.byteshare.ingredient.models.Ingredient;
import com.revature.byteshare.ingredient.models.Macros;
import com.revature.byteshare.util.aspects.ExceptionAspect;
import com.revature.byteshare.util.exceptions.NutritionixException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest(classes = {IngredientController.class})
@AutoConfigureMockMvc
@EnableWebMvc
public class IngredientControllerIntegrationTestSuite {
    @MockBean
    private IngredientRepository ingredientRepository;
    @MockBean
    private IngredientService ingredientService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private IngredientController ingredientController;

    private final String hamburger = "hamburger";
    private final Ingredient hamburgerIngredient = new Ingredient(hamburger, 608, "image:url");
    private final Macros hamburgerMacros = new Macros(1.0, "sandwich", 226.0,
            540.14, 26.56, 34.28, 40.27, 0.0);

    private final String hamburgerNoMacrosJson = "{\"ingredientName\":\"hamburger\",\"tagId\":608,\"photoURL\":\"image:url\",\"macros\":{\"servingQuantity\":0.0,\"servingUnit\":null,\"gramsPerServing\":0.0,\"calories\":0.0,\"fat\":0.0,\"protein\":0.0,\"totalCarbs\":0.0,\"sugars\":0.0}}";
    private final String hamburgerWithMacrosJson = "{\"ingredientName\":\"hamburger\",\"tagId\":608,\"photoURL\":\"image:url\",\"macros\":{\"servingQuantity\":1.0,\"servingUnit\":\"sandwich\",\"gramsPerServing\":226.0,\"calories\":540.14,\"fat\":26.56,\"protein\":34.28,\"totalCarbs\":40.27,\"sugars\":0.0}}";
    private final String hamburgerMacrosJson = "{\"servingQuantity\":1.0,\"servingUnit\":\"sandwich\",\"gramsPerServing\":226.0,\"calories\":540.14,\"fat\":26.56,\"protein\":34.28,\"totalCarbs\":40.27,\"sugars\":0.0}";

    @BeforeEach
    public void setUp() {
        hamburgerIngredient.setMacros(null);
    }

    @Test
    public void testSearchForHamburger() throws Exception {
        when(ingredientService.searchFor(hamburger)).thenReturn(List.of(hamburgerIngredient));
        String expectedResult = "[" + hamburgerNoMacrosJson + "]";

        mockMvc.perform(MockMvcRequestBuilders.get(("/ingredients/hamburger"))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
                .andExpect(MockMvcResultMatchers.content().string(expectedResult));
    }

    @Test
    public void testPopulateHamburgerMacros() throws Exception {
        Ingredient fullHamburger = Ingredient.builder()
                .ingredientName(hamburgerIngredient.getIngredientName())
                .tagId(hamburgerIngredient.getTagId())
                .photoURL(hamburgerIngredient.getPhotoURL())
                .macros(hamburgerMacros)
                .build();
        when(ingredientService.populateMacros(hamburgerIngredient)).thenReturn(fullHamburger);

        mockMvc.perform(MockMvcRequestBuilders.post("/ingredients/macros")
                .contentType(MediaType.APPLICATION_JSON)
                .content(hamburgerNoMacrosJson))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
                .andExpect(MockMvcResultMatchers.content().string(hamburgerWithMacrosJson));

    }

    @Test
    public void testSearchHamburgerMacros() throws Exception {
        when(ingredientService.getMacrosFor(hamburger)).thenReturn(hamburgerMacros);

        mockMvc.perform(MockMvcRequestBuilders.get(("/ingredients/macros?ingredientName="+hamburger)))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
                .andExpect(MockMvcResultMatchers.content().string(hamburgerMacrosJson));
    }

    @Test
    public void testThrowsNutritionixException() throws Exception {
        when(ingredientService.getMacrosFor(hamburger)).thenThrow(NutritionixException.class);

        mockMvc.perform(MockMvcRequestBuilders.get(("/ingredients/macros?ingredientName="+hamburger)))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.FAILED_DEPENDENCY.value()));
    }
}
