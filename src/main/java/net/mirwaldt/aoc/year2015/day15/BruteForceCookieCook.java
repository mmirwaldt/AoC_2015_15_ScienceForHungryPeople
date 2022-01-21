package net.mirwaldt.aoc.year2015.day15;

import java.util.SortedMap;

public class BruteForceCookieCook extends AbstractCookieCook {
    @Override
    protected CookieRecipe selectCookieRecipe(SortedMap<String, Integer> usedIngredients, CookieRecipe lastRecipe) {
        return selectCookieRecipeWithHigherScore(usedIngredients, lastRecipe);
    }
}
