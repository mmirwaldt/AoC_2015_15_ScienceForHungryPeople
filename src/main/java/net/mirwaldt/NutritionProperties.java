package net.mirwaldt;

import java.util.Objects;

import static java.lang.Math.max;

public class NutritionProperties {
    private final int capacity;
    private final int durability;
    private final int flavor;
    private final int texture;
    private final int calories;

    public NutritionProperties(int capacity, int durability, int flavor, int texture, int calories) {
        this.capacity = capacity;
        this.durability = durability;
        this.flavor = flavor;
        this.texture = texture;
        this.calories = calories;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getDurability() {
        return durability;
    }

    public int getFlavor() {
        return flavor;
    }

    public int getTexture() {
        return texture;
    }

    public int getCalories() {
        return calories;
    }

    public int calculateScore() {
        return max(0, capacity) * max(0, durability) * max(0, flavor) * max(0, texture);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NutritionProperties that = (NutritionProperties) o;
        return capacity == that.capacity && durability == that.durability && flavor == that.flavor
                && texture == that.texture && calories == that.calories;
    }

    @Override
    public int hashCode() {
        return Objects.hash(capacity, durability, flavor, texture, calories);
    }

    @Override
    public String toString() {
        return "NutritionProperties{" +
                "capacity=" + capacity +
                ", durability=" + durability +
                ", flavor=" + flavor +
                ", texture=" + texture +
                ", calories=" + calories +
                '}';
    }
}
