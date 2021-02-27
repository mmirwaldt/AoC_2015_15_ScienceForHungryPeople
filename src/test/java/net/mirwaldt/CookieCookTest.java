package net.mirwaldt;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CookieCookTest {
    private static Stream<Arguments> cookieCook() {
        return Stream.of(Arguments.of(new BruteForceCookieCook()));
    }

    @ParameterizedTest
    @MethodSource("cookieCook")
    void test_example(CookieCook cookieCook) {
        /*
            Butterscotch: capacity -1, durability -2, flavor 6, texture 3, calories 8
            Cinnamon: capacity 2, durability 3, flavor -2, texture -1, calories 3
         */
        cookieCook.addIngredient("Butterscotch", -1, -2, 6, 3, 8);
        cookieCook.addIngredient("Cinnamon", 2, 3, -2, -1, 3);
        final Map<String, Integer> teasSpoonsByIngredients = Map.of("Butterscotch", 44, "Cinnamon", 56);
        final NutritionProperties cookieProperties =
                new NutritionProperties(68, 80, 152, 76, 0);
        assertEquals(new CookieRecipe(teasSpoonsByIngredients, cookieProperties), cookieCook.cook(100));
    }
}
