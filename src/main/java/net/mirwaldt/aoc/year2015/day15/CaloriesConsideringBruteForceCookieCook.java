package net.mirwaldt.aoc.year2015.day15;

import java.util.SortedMap;

public class CaloriesConsideringBruteForceCookieCook extends AbstractCookieCook {
    private final int expectedCalories;

    public CaloriesConsideringBruteForceCookieCook(int expectedCalories) {
        this.expectedCalories = expectedCalories;
    }

    @Override
    protected CookieRecipe selectCookieRecipe(SortedMap<String, Integer> usedIngredients, CookieRecipe lastRecipe) {
        final NutritionProperties nutritionProperties = calculateNutritionProperties(usedIngredients);
        if(nutritionProperties.getCalories() == expectedCalories) {
            return selectCookieRecipeWithHigherScore(usedIngredients, nutritionProperties, lastRecipe);
        } else {
            return lastRecipe;
        }
    }
}
