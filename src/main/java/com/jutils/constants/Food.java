package com.jutils.constants;

/**
 * 通过接口来对枚举进行分组，每个枚举依然保持  Food 类型
 */
public interface Food {
    enum Appetizer implements Food {
        SALAD,
        SOUP,
        SPRING_ROLLS;
    }

    enum MainCourse implements Food {
        LASAGNE,
        BURRITO,
        PAD_THAI;
    }

    enum Dessert implements Food {
        TIRAMISU,
        GELATO,
        BLACK_FOREST_CAKE,
        FRUIT,
        CREME_CARAMEL;
    }

    enum Coffee implements Food {
        BLACK,
        DECAF,
        ESPRESSO,
        LATTE,
        CAPPUCCINE,
        TEA,
        HERB_TEA;
    }
}

class TypeOfFood {
    public static void main(String[] args) {
        Food food = Food.Appetizer.SALAD;
        food = Food.MainCourse.LASAGNE;
    }
}

enum Course {
    APPETIZER(Food.Appetizer.class),
    MAINCOURSE(Food.MainCourse.class),
    DESSERT(Food.Dessert.class),
    COFFEE(Food.Coffee.class);

    private Food[] foods;

    Course(Class<? extends Food> aClass) {
        foods = aClass.getEnumConstants();
    }

    public Food randomSelect() {
        return EnumUtils.random(foods);
    }
}