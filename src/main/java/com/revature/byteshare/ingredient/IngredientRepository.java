package com.revature.byteshare.ingredient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.byteshare.ingredient.models.*;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is not a traditional repository and as such does not inherit JPARepository
 * Instead it will query the nutritionix API and return the results
 * https://docx.syndigo.com/developers/docs/natural-language-for-nutrients
 */

@Repository
public class IngredientRepository {
    private final String baseURL = "https://trackapi.nutritionix.com";
    private final String searchEndpoint = "/v2/search/instant?query=";
    private final String nutritionEndpoint = "/v2/natural/nutrients";

    @Value("${nutritionix.app-id}")
    private String appID;
    @Value("${nutritionix.app-key}")
    private String appKey;

    @Setter //setter is used for testing purposes
    private HttpClient client;

    public IngredientRepository() {
        this.client = HttpClient.newHttpClient();
    }


    public List<Ingredient> searchFor(String search) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder(
                URI.create(baseURL+searchEndpoint+search))
                .header("accept", "application/json")
                .header("x-app-id", appID)
                .header("x-app-key", appKey)
                .header("x-remote-user-id", "0").build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return getCommonIngredientsFromJson(response.body());
    }

    public Macros getMacrosFor(String ingredientName) throws IOException, InterruptedException{
        String body = String.format("{\"query\": \"%s\"}", ingredientName);
        HttpRequest request = HttpRequest.newBuilder(
                        URI.create(baseURL+nutritionEndpoint))
                .header("accept", "application/json")
                .header("x-app-id", appID)
                .header("x-app-key", appKey)
                .header("x-remote-user-id", "0")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return getMacrosFromJson(response.body());
    }

    private List<Ingredient> getCommonIngredientsFromJson(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        SearchResponse response = mapper.readValue(json, SearchResponse.class);
//        json = json.substring(json.indexOf("[")+2, json.indexOf("]")-1);

        List<IngredientDTO> ingredientDTOs = response.getCommonFoods();

        List<Ingredient> ingredients = new ArrayList<>();
        for (IngredientDTO dto : ingredientDTOs) {
            ingredients.add(DTOconverter(dto));
        }
        return ingredients;
    }

    private Ingredient DTOconverter(IngredientDTO dto){
        return new Ingredient(dto.getFoodName(), dto.getTagId(), dto.getThumb());
    }

    private Macros getMacrosFromJson(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        NutritionResponse response = mapper.readValue(json, NutritionResponse.class);

        return NutritionResponseConverter(response);
    }

    private Macros NutritionResponseConverter(NutritionResponse response){
        MacrosDTO food = response.getFoods().get(0);
        return new Macros(food.getServingQty(), food.getServingUnit(), food.getGramsPerServing(),
                food.getCalories(), food.getFat(), food.getProtein(),
                food.getTotalCarbs(), food.getSugars());
    }
}
