package net.mirwaldt;

import java.util.Objects;

public class CookieIngredient {
    private final String name;
    private final NutritionProperties properties;

    public CookieIngredient(String name, NutritionProperties properties) {
        this.name = name;
        this.properties = properties;
    }

    public String getName() {
        return name;
    }

    public NutritionProperties getProperties() {
        return properties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CookieIngredient that = (CookieIngredient) o;
        return Objects.equals(name, that.name) && Objects.equals(properties, that.properties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, properties);
    }

    @Override
    public String toString() {
        return "CookieIngredient{" +
                "name='" + name + '\'' +
                ", properties=" + properties +
                '}';
    }
}
