package com.revature.byteshare.ingredient;

import org.springframework.stereotype.Repository;

/**
 * This class is not a traditional repository and as such does not inherit JPARepository
 * Instead it will query the nutritionix API and return the results
 * https://docx.syndigo.com/developers/docs/natural-language-for-nutrients
 */
@Repository
public class IngredientRepository {
}
