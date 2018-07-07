package com.jutils.constants;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by einverne on 7/7/18.
 */
interface Next {
    Fruit next();
}

public enum Fruit implements Next {
    APPLE("apple") {
        @Override
        public Fruit next() {
            return nextValue(ordinal());
        }
    },
    PEAR("pear") {
        @Override
        public Fruit next() {
            return Fruit.nextValue(ordinal());
        }
    };

    private static Fruit nextValue(int ordinal) {
        final int nextIndex = (ordinal + 1) % values().length;
        Fruit fruit = values()[nextIndex];
        return fruit;
    }

    private String type;

    public String getType() {
        return type;
    }

    Fruit(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        String name = name();
        String lower = name.substring(1).toLowerCase();
        return name.charAt(0) + lower;
    }

    public static void main(String[] args) {
        for (Fruit f : values()) {
            print(f + " ordinal: " + f.ordinal());
            print(f.getDeclaringClass().getName());
            print(f.name());
            print(f.toString());
        }

        Fruit apple = Enum.valueOf(Fruit.class, "APPLE");

        Set<String> methods = analyze(Fruit.class);
        Set<String> enumMethods = analyze(Enum.class);


        printNext(apple);
        printNext(Fruit.PEAR);

        Fruit random = EnumUtils.random(Fruit.class);
        print(random);
    }

    public static void printNext(Next n) {
        print(n.next());
    }

    static void print(Object s) {
        System.out.println(s);
    }

    static Set<String> analyze(Class<?> enumClass) {
        print("-----------------Analyzing " + enumClass + " --------------------");
        print("Interfaces: ");
        for (Type t : enumClass.getGenericInterfaces()) {
            print(t);
        }
        print("Base: " + enumClass.getSuperclass());
        print("Methods: ");
        Set<String> methods = new TreeSet<>();
        for (Method m : enumClass.getMethods()) {
            methods.add(m.getName());
        }
        print(methods);
        return methods;
    }
}
