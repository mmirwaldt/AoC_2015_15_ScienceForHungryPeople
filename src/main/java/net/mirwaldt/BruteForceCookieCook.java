package net.mirwaldt;

import java.util.SortedMap;

public class BruteForceCookieCook extends AbstractCookieCook {
    @Override
    protected CookieRecipe selectCookieRecipe(SortedMap<String, Integer> usedIngredients, CookieRecipe lastRecipe) {
        return selectCookieRecipeWithHigerScore(usedIngredients, lastRecipe);
    }
}
