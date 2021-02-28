package net.mirwaldt;

import java.util.*;

import static java.lang.Math.max;

public class BruteForceCookieCook implements CookieCook {
    private final SortedMap<String, CookieIngredient> ingredientsByNames = new TreeMap<>();

    @Override
    public void addIngredient(String name, int capacity, int durability, int flavor, int texture, int calories) {
        final NutritionProperties nutritionProperties =
                new NutritionProperties(capacity, durability, flavor, texture, calories);
        ingredientsByNames.put(name, new CookieIngredient(name, nutritionProperties));
    }

    @Override
    public CookieRecipe cook(int teaSpoons) {
        final List<CookieRecipe> cookieRecipes = new ArrayList<>();
        cookRecursively(Collections.emptySortedMap(), teaSpoons, cookieRecipes);
        cookieRecipes.sort(createCookieScoreComparator());
        return cookieRecipes.get(0);
    }

    private Comparator<CookieRecipe> createCookieScoreComparator() {
        return Comparator.<CookieRecipe>comparingInt(cookieRecipe ->
                cookieRecipe.getProperties().calculateScoreWithoutCalories()).reversed();
    }

    private void cookRecursively(
            SortedMap<String, Integer> usedIngredients, int remainingTeaSpoons, List<CookieRecipe> recipes) {
        if (usedIngredients.size() == ingredientsByNames.size()) {
            final NutritionProperties nutritionProperties = calculateNutritionProperties(usedIngredients);
            recipes.add(new CookieRecipe(usedIngredients, nutritionProperties));
        } else {
            final SortedMap<String, CookieIngredient> remainingIngredientsByNames = new TreeMap<>(ingredientsByNames);
            remainingIngredientsByNames.keySet().removeAll(usedIngredients.keySet());
            for (CookieIngredient ingredient : remainingIngredientsByNames.values()) {
                for (int teaSpoons = 1; teaSpoons <= remainingTeaSpoons; teaSpoons++) {
                    final SortedMap<String, Integer> newUsedIngredients = new TreeMap<>(usedIngredients);
                    newUsedIngredients.put(ingredient.getName(), teaSpoons);
                    cookRecursively(newUsedIngredients, remainingTeaSpoons - teaSpoons, recipes);
                }
            }
        }
    }

    private NutritionProperties calculateNutritionProperties(Map<String, Integer> usedIngredients) {
        int capacity = 0;
        int durability = 0;
        int flavor = 0;
        int texture = 0;

        for (Map.Entry<String, Integer> ingredientsEntry : usedIngredients.entrySet()) {
            final String ingredientName = ingredientsEntry.getKey();
            final Integer teaSpoons = ingredientsEntry.getValue();

            final CookieIngredient ingredient = ingredientsByNames.get(ingredientName);
            final NutritionProperties properties = ingredient.getProperties();

            capacity += properties.getCapacity() * teaSpoons;
            durability += properties.getDurability() * teaSpoons;
            flavor += properties.getFlavor() * teaSpoons;
            texture += properties.getTexture() * teaSpoons;
        }

        capacity = max(0, capacity);
        durability = max(0, durability);
        flavor = max(0, flavor);
        texture = max(0, texture);

        return new NutritionProperties(capacity, durability, flavor, texture, 0);
    }
}
