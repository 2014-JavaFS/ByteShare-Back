package com.revature.byteshare.ingredient;

import com.revature.byteshare.ingredient.models.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {IngredientRepository.class})
public class IngredientRepositoryTests {

    @Mock
    private HttpClient client;

    @Mock
    private HttpResponse<String> mockResponse;

    @Autowired
    private IngredientRepository repo;



    private final String hamburger = "hamburger";
    private final Macros hamburgerMacros = new Macros(1.0, "sandwich", 226.0,
            540.14, 26.56, 34.28, 40.27, 0.0);
    private final Ingredient hamburgerIngredient = new Ingredient(hamburger, 608, "image:url");

    @BeforeEach
    public void setUp() {
        repo.setClient(client);
    }

    @Test
    public void testIngredientSearch() throws IOException, InterruptedException {
        String searchResponseJson = "{\"common\":[{\"food_name\":\"hamburger\",\"serving_unit\":\"sandwich\",\"serving_qty\":1," +
                "\"tag_name\":\"hamburger\",\"tag_id\":\"608\",\"photo\":{\"thumb\":\"image:url\"}}]," +
                "\"branded\":[]}";
        doReturn(searchResponseJson).when(mockResponse).body();
        doReturn(mockResponse).when(client).send(any(), any());

        List<Ingredient> result = repo.searchFor("hamburger");

        assertNotNull(result);
        assertEquals(hamburgerIngredient, result.get(0));
    }

    @Test
    public void testGetMacros() throws Exception{
        String nutrientJson = "{\"foods\":[{\"food_name\":\"hamburger\",\"brand_name\":null,\"serving_qty\":1," +
                "\"serving_unit\":\"sandwich\",\"serving_weight_grams\":226,\"nf_calories\":540.14,\"nf_total_fat\":" +
                "26.56,\"nf_saturated_fat\":10.52,\"nf_cholesterol\":122.04,\"nf_sodium\":791,\"nf_total_carbohydrate\"" +
                ":40.27,\"nf_dietary_fiber\":null,\"nf_sugars\":null,\"nf_protein\":34.28}]}";
        doReturn(nutrientJson).when(mockResponse).body();
        doReturn(mockResponse).when(client).send(any(), any());

        Macros result = repo.getMacrosFor(hamburger);

        assertNotNull(result);
        assertEquals(hamburgerMacros, result);
    }
}
