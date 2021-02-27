package net.mirwaldt;

import java.util.Collections;

public class BruteForceCookieCook implements CookieCook {
    @Override
    public void addIngredient(String name, int capacity, int durability, int flavor, int texture, int calories) {

    }

    @Override
    public CookieRecipe cook(int teaSpoons) {
        return new CookieRecipe(Collections.emptyMap(),
                new NutritionProperties(0, 0, 0, 0, 0));
    }
}
