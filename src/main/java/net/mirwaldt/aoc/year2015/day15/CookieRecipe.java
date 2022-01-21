package net.mirwaldt.aoc.year2015.day15;

import java.util.Map;
import java.util.Objects;

public class CookieRecipe {
    private final Map<String, Integer> teasSpoonsByIngredients;
    private final NutritionProperties properties;

    public CookieRecipe(Map<String, Integer> teasSpoonsByIngredients, NutritionProperties properties) {
        this.teasSpoonsByIngredients = teasSpoonsByIngredients;
        this.properties = properties;
    }

    public Map<String, Integer> getTeasSpoonsByIngredients() {
        return teasSpoonsByIngredients;
    }

    public NutritionProperties getProperties() {
        return properties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CookieRecipe that = (CookieRecipe) o;
        return Objects.equals(teasSpoonsByIngredients, that.teasSpoonsByIngredients)
                && Objects.equals(properties, that.properties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teasSpoonsByIngredients, properties);
    }

    @Override
    public String toString() {
        return "CookieRecipe{" +
                "teasSpoonsByIngredients=" + teasSpoonsByIngredients +
                ", properties=" + properties +
                '}';
    }
}
