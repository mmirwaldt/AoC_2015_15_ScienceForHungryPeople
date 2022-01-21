package net.mirwaldt.aoc.year2015.day15;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CookieCookMain {
    public static void main(String[] args) throws IOException {
        final List<String> lines = Files.readAllLines(Path.of("input.txt"), StandardCharsets.US_ASCII);
        final CookieCook partOneCookieCook = new BruteForceCookieCook();
        addIngredients(lines, partOneCookieCook);

        final CookieRecipe partOneRecipe = partOneCookieCook.cook(100);

        // {Butterscotch=19, Candy=26, Chocolate=38, Sprinkles=17}
        System.out.println(partOneRecipe.getTeasSpoonsByIngredients());

        //NutritionProperties{capacity=34, durability=69, flavor=99, texture=92, calories=0}
        System.out.println(partOneRecipe.getProperties());

        // 21367368
        System.out.println(partOneRecipe.getProperties().calculateScore());

        final CookieCook partTwoCookieCook = new CaloriesConsideringBruteForceCookieCook(500);
        addIngredients(lines, partTwoCookieCook);

        final CookieRecipe partTwoRecipe = partTwoCookieCook.cook(100);

        // {Butterscotch=14, Candy=10, Chocolate=30, Sprinkles=46}
        System.out.println(partTwoRecipe.getTeasSpoonsByIngredients());

        // NutritionProperties{capacity=92, durability=60, flavor=16, texture=20, calories=500}
        System.out.println(partTwoRecipe.getProperties());

        // 1766400
        System.out.println(partTwoRecipe.getProperties().calculateScore());
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
