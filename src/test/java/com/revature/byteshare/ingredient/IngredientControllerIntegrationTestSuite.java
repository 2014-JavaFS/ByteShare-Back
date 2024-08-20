package com.revature.byteshare.ingredient;

import com.revature.byteshare.ingredient.models.Ingredient;
import com.revature.byteshare.ingredient.models.Macros;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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

    private final String hamburgerNoMacrosJson = "";

    @Test
    public void testSearchForHamburger() throws Exception {
        when(ingredientService.searchFor(hamburger)).thenReturn(List.of(hamburgerIngredient));
        String expectedResult = "[" + hamburgerNoMacrosJson + "]";

        mockMvc.perform(MockMvcRequestBuilders.get(("/ingredients/hamburger"))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().string(expectedResult));
    }

    @Test
    public void testPopulateHamburgerMacros() throws Exception {

    }

    @Test
    public void testSearchHamburgerMacros() throws Exception {

    }
}
