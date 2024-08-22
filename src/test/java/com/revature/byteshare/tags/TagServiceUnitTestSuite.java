package com.revature.byteshare.tags;

import com.revature.byteshare.recipe.Recipe;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TagServiceUnitTestSuite {

    @Mock
    private TagRepository tagRepository;
    @InjectMocks
    private TagService tagService;

    @Mock
    private static Recipe defaultRecipe = new Recipe();

    private static Tag defaultTag = new Tag(1, defaultRecipe,"dummyTagOne");

    @Test
    public void TestSuccessFindAllTags(){

    }
    @Test
    public void TestFailFindAllTags(){

    }
    @Test
    public void TestSuccessFindAllTagNamesByRecipe(){

    }
    @Test
    public void TestFailFindAllTagNamesByRecipe(){

    }
    @Test
    public void TestSuccessFindAllRecipesByTag(){

    }
    @Test
    public void TestFailFindAllRecipesByTag(){

    }
    @Test
    public void TestSuccessCreateTag(){

    }
    @Test
    public void TestFailCreateTag(){

    }
    @Test
    public void TestSuccessDeleteTag(){

    }
    @Test
    public void TestFailDeleteTag(){

    }
    @Test
    public void TestSuccessUpdateTag(){

    }
    @Test
    public void TestFailUpdateTag(){

    }
    @Test
    public void TestSuccessFindTagById(){

    }
    @Test
    public void TestFailFindTagById(){

    }

}
