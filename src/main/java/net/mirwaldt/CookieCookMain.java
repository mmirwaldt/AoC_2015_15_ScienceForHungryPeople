package net.mirwaldt;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CookieCookMain {
    public static void main(String[] args) throws IOException {
        final List<String> lines = Files.readAllLines(Path.of("input.txt"), StandardCharsets.US_ASCII);
        final CookieCook cookieCook = new BruteForceCookieCook();
        addIngredients(lines, cookieCook);

        final CookieRecipe recipe = cookieCook.cook(100);

        System.out.println(recipe.getTeasSpoonsByIngredients());
        System.out.println(recipe.getProperties());
        System.out.println(recipe.getProperties().calculateScoreWithoutCalories());
    }

    public static void addIngredients(List<String> lines, CookieCook cookieCook) {
        for (String line : lines) {
            final String[] tokens = line.split(" ");
            final String name = tokens[0].replace(":", "");
            final int capacity = Integer.parseInt(tokens[2].replace(",", ""));
            final int durability = Integer.parseInt(tokens[4].replace(",", ""));
            final int flavor = Integer.parseInt(tokens[6].replace(",", ""));
            final int texture = Integer.parseInt(tokens[8].replace(",", ""));
            final int calories = Integer.parseInt(tokens[10]);
            cookieCook.addIngredient(name, capacity, durability, flavor, texture, calories);
        }
    }
}
