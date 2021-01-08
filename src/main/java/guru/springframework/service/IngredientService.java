package guru.springframework.service;

import guru.springframework.domain.Ingredient;

import java.util.Set;

public interface IngredientService {
    Set<Ingredient> getIngredients();
}
