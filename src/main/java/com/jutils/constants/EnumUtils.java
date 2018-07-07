package com.jutils.constants;

import java.util.Random;

/**
 * Created by einverne on 7/7/18.
 */
public class EnumUtils {
    public static <T extends Enum<T>> T random(Class<T> c) {
        return random(c.getEnumConstants());
    }

    public static <T extends Enum<T>> T random(T[] values) {
        return values[new Random().nextInt(values.length)];
    }

    public static Food random(Food[] foods) {
        return foods[new Random().nextInt(foods.length)];
    }
}
