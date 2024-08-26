package com.revature.byteshare.tags;

import com.revature.byteshare.recipe.Recipe;
import com.revature.byteshare.tags.models.Tag;
import com.revature.byteshare.util.exceptions.DataNotFoundException;
import com.revature.byteshare.util.exceptions.InvalidInputException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

        when(tagRepository.findAll()).thenReturn(List.of(defaultTag));

        List<Tag> result = tagService.findAllTags();

        assertEquals(1, result.size());
        assertEquals(defaultTag, result.get(0));
    }
    @Test
    public void TestFailFindAllTags(){
        when(tagRepository.findAll()).thenReturn(List.of());

        assertThrows(DataNotFoundException.class, ()-> tagService.findAllTags());

        verify(tagRepository, times(1)).findAll();
    }
    @Test
    public void TestSuccessFindAllTagNamesByRecipe(){
        when(tagRepository.findAllTagNamesByRecipe(defaultRecipe))
                .thenReturn(Optional.of(List.of(defaultTag.getTag_name())));

        List<String> result = tagService.findAllTagNamesByRecipe(defaultRecipe);

        assertEquals(1, result.size());
        assertEquals(defaultTag.getTag_name(), result.get(0));
    }
    @Test
    public void TestFailFindAllTagNamesByRecipe(){
        when(tagRepository.findAllTagNamesByRecipe(defaultRecipe))
                .thenReturn(Optional.empty());

        assertThrows(DataNotFoundException.class, ()-> tagService.findAllTagNamesByRecipe(defaultRecipe));

        verify(tagRepository, times(1)).findAllTagNamesByRecipe(defaultRecipe);
    }
    @Test
    public void TestSuccessFindAllRecipesByTag(){
        when(tagRepository.findAllRecipesByTagNames(defaultTag.getTag_name())).
                thenReturn(Optional.of(List.of(defaultRecipe)));

        List<Recipe> result = tagService.findAllRecipesByTagName(defaultTag.getTag_name());

        assertEquals(1, result.size());
        assertEquals(defaultRecipe, result.get(0));
    }
    @Test
    public void TestFailFindAllRecipesByTag(){
        when(tagRepository.findAllRecipesByTagNames(defaultTag.getTag_name())).
                thenReturn(Optional.empty());

        assertThrows(DataNotFoundException.class, ()-> tagService.findAllRecipesByTagName(defaultTag.getTag_name()));

        verify(tagRepository, times(1)).findAllRecipesByTagNames(defaultTag.getTag_name());
    }
    @Test
    public void TestSuccessCreateTag(){
        when(tagRepository.save(defaultTag)).thenReturn(defaultTag);

        Tag result = tagService.create(defaultTag);

        assertEquals(defaultTag, result);
    }
    @Test
    public void TestFailCreateTag(){
        //when(tagRepository.save(defaultTag)).thenReturn(defaultTag);
        when(tagRepository.findById(defaultTag.getTag_id())).thenReturn(Optional.of(defaultTag));

        assertThrows(InvalidInputException.class, ()-> tagService.create(defaultTag));
    }
    @Test
    public void TestSuccessDeleteTag(){
        // Arrange
        int tagId = 1;
        Tag tag = new Tag();
        tag.setTag_id(tagId);
        when(tagRepository.findById(tagId)).thenReturn(Optional.of(tag));

        // Act
        boolean result = tagService.delete(tagId);

        // Assert
        assertTrue(result);
        verify(tagRepository, times(1)).findById(tagId);
        verify(tagRepository, times(1)).deleteById(tagId);
    }
    @Test
    public void TestFailDeleteTag(){
        int tagId = 1;
        when(tagRepository.findById(tagId)).thenReturn(Optional.empty());

        assertThrows(DataNotFoundException.class, ()-> tagService.delete(tagId));
    }
}
