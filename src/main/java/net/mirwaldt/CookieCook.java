package net.mirwaldt;

public interface CookieCook {
    void addIngredient(String name, int capacity, int durability, int flavor, int texture, int calories);
    CookieRecipe cook(int teaSpoons);
}
