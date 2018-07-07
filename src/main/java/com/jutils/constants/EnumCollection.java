package com.jutils.constants;

import java.util.EnumMap;
import java.util.EnumSet;

/**
 * Created by einverne on 7/7/18.
 */
public class EnumCollection {
    public static void main(String[] args) {
        EnumSet<Fruit> set = EnumSet.noneOf(Fruit.class);
        set.add(Fruit.APPLE);
        set.add(Fruit.PEAR);

        EnumMap<Fruit, String> m = new EnumMap<Fruit, String>(Fruit.class);
        m.put(Fruit.APPLE, "red");
        m.put(Fruit.PEAR, "yellow");
    }
}
