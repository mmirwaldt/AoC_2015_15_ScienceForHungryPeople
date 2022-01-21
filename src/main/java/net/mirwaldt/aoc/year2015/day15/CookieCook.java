package net.mirwaldt.aoc.year2015.day15;

public interface CookieCook {
    void addIngredient(String name, int capacity, int durability, int flavor, int texture, int calories);
    CookieRecipe cook(int teaSpoons);
}
