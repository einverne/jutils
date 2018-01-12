package com.jutils.gson.strategy;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.jutils.gson.entity.Car;
import com.jutils.gson.entity.PersonStrategy;

/**
 * ExclusionStrategy
 *
 * A strategy (or policy) definition that is used to decide whether or not a field or top-level class should be serialized or deserialized as part of the JSON output/input.
 */
public class ExcluStrategy implements ExclusionStrategy {
    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        // ignore id field
        if (f.getDeclaringClass() == PersonStrategy.class && f.getName().equals("id")) {
            return true;
        }
        return false;
    }

    @Override
    public boolean shouldSkipClass(Class<?> aClass) {
        if (aClass == Car.class) {
            return true;
        }
        return false;
    }
}
