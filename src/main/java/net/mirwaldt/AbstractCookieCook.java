package net.mirwaldt;

import java.util.Collections;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import static java.lang.Math.max;

public abstract class AbstractCookieCook implements CookieCook {
    protected final SortedMap<String, CookieIngredient> ingredientsByNames = new TreeMap<>();

    @Override
    public void addIngredient(String name, int capacity, int durability, int flavor, int texture, int calories) {
        final NutritionProperties nutritionProperties =
                new NutritionProperties(capacity, durability, flavor, texture, calories);
        ingredientsByNames.put(name, new CookieIngredient(name, nutritionProperties));
    }

    @Override
    public CookieRecipe cook(int teaSpoons) {
        final NutritionProperties initialProperties =
                new NutritionProperties(0, 0, 0, 0, 0);
        return cookRecursively(Collections.emptySortedMap(), teaSpoons,
                new CookieRecipe(Collections.emptySortedMap(), initialProperties));
    }

    protected CookieRecipe cookRecursively(
            SortedMap<String, Integer> usedIngredients, int remainingTeaSpoons, CookieRecipe lastRecipe) {
        if (usedIngredients.size() == ingredientsByNames.size()) {
            return selectCookieRecipe(usedIngredients, lastRecipe);
        } else {
            final SortedMap<String, CookieIngredient> remainingIngredientsByNames = new TreeMap<>(ingredientsByNames);
            remainingIngredientsByNames.keySet().removeAll(usedIngredients.keySet());
            CookieRecipe nextRecipe = lastRecipe;
            for (CookieIngredient ingredient : remainingIngredientsByNames.values()) {
                int teaSpoons = (1 < remainingIngredientsByNames.size()) ? 1 : remainingTeaSpoons;
                for (; teaSpoons <= remainingTeaSpoons; teaSpoons++) {
                    final SortedMap<String, Integer> newUsedIngredients = new TreeMap<>(usedIngredients);
                    newUsedIngredients.put(ingredient.getName(), teaSpoons);
                    nextRecipe = cookRecursively(newUsedIngredients,
                            remainingTeaSpoons - teaSpoons, nextRecipe);
                }
            }
            return nextRecipe;
        }
    }

    protected NutritionProperties calculateNutritionProperties(Map<String, Integer> usedIngredients) {
        int capacity = 0;
        int durability = 0;
        int flavor = 0;
        int texture = 0;
        int calories = 0;

        for (Map.Entry<String, Integer> ingredientsEntry : usedIngredients.entrySet()) {
            final String ingredientName = ingredientsEntry.getKey();
            final Integer teaSpoons = ingredientsEntry.getValue();

            final CookieIngredient ingredient = ingredientsByNames.get(ingredientName);
            final NutritionProperties properties = ingredient.getProperties();

            capacity += properties.getCapacity() * teaSpoons;
            durability += properties.getDurability() * teaSpoons;
            flavor += properties.getFlavor() * teaSpoons;
            texture += properties.getTexture() * teaSpoons;
            calories += properties.getCalories() * teaSpoons;
        }

        capacity = max(0, capacity);
        durability = max(0, durability);
        flavor = max(0, flavor);
        texture = max(0, texture);

        return new NutritionProperties(capacity, durability, flavor, texture, calories);
    }

    protected CookieRecipe selectCookieRecipeWithHigerScore(
            SortedMap<String, Integer> usedIngredients, CookieRecipe lastRecipe) {
        final NutritionProperties nutritionProperties = calculateNutritionProperties(usedIngredients);
        final int scoreOfNewRecipe = nutritionProperties.calculateScore();
        final int scoreOfLastRecipe = lastRecipe.getProperties().calculateScore();
        if (scoreOfLastRecipe < scoreOfNewRecipe) {
            return new CookieRecipe(usedIngredients, nutritionProperties);
        } else {
            return lastRecipe;
        }
    }

    protected abstract CookieRecipe selectCookieRecipe(
            SortedMap<String, Integer> usedIngredients, CookieRecipe lastRecipe);
}
