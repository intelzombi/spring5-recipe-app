package guru.springframework.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryTest {

    Category category;

    @Before
    public void setUp(){
        category = new Category();
    }

    @Test
    public void getId() throws Exception{
        Long idValue = 4L;

        category.setId(idValue);
        assertEquals(idValue, category.getId());
    }

    @Test
    public void getCategoryName() throws Exception{
        String nameValue = "American";
        category.setCategoryName(nameValue);
        assertEquals(nameValue, category.getCategoryName());
    }

    @Test
    public void getRecipies() throws Exception{
//        Recipe recipe = new Recipe();
//        recipe.setId(1L);
//        recipe.setDescription("ty tacos");
//
//        category.addRecipe(recipe);
//        assertEquals(recipe, category.getRecipies().contains(recipe));
    }
}