package com.revature.byteshare.Recipe;

import com.revature.byteshare.User.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RecipeControllerIntegrationTestSuite {
    @Mock
    private RecipeRepository mockRecipeRepository;

    @InjectMocks
    RecipeService sut;

    private static Time now = Time.valueOf(LocalTime.now());
    private static User defaultUser = new User(1, "ruben@mail.com", "pasword123!", "ruben", "Ruben", "Fitch", "", User.userType.AUTHOR);

    private String userJSON = "{\"id\":1,\"email\":\"ruben@mail.com\",\"password\":\"pasword123!\",\"username\":\"ruben\",\"first_name\":\"Ruben\",\"last_name\":\"Fitch\",\"auth_token\":\"\",\"user_type\":\"AUTHOR\"}";


    private static Recipe defaultRecipe = new Recipe(1, defaultUser, now, "This is a recipe", 10, 20);
    private String recipeJSON = "{\"id\":1,\"author\":" + userJSON + ",\"date\":\"" + now + "\",\"content\":\"This is a recipe\",\"prepTime\":10,\"cookTime\":20}";

    @Test
    public void testFindAll(){
        List<Recipe> recipes = List.of(defaultRecipe);
        when(mockRecipeRepository.findAll()).thenReturn(recipes);

        List<Recipe> actual = sut.findAll();
        assertEquals(1, actual.size());
        assertEquals(defaultRecipe, actual.get(0));
    }

    @Test
    public void testCreate(){
        when(mockRecipeRepository.save(defaultRecipe)).thenReturn(defaultRecipe);

        Recipe actual = sut.create(defaultRecipe);
        assertEquals(defaultRecipe, actual);
    }

    @Test
    public void testFindById() {
        when(mockRecipeRepository.findById(1)).thenReturn(java.util.Optional.of(defaultRecipe));

        Recipe actual = sut.findById(1);
        assertEquals(defaultRecipe, actual);
    }

    @Test
    public void testUpdate(){
        when(mockRecipeRepository.save(defaultRecipe)).thenReturn(defaultRecipe);

        boolean actual = sut.update(defaultRecipe);
        assertEquals(true, actual);
    }
}
