package com.jutils.gson;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/**
 * Created by mi on 18-1-12.
 */
public class SerializeExclusionStrategy implements ExclusionStrategy {
    @Override
    public boolean shouldSkipField(FieldAttributes fieldAttributes) {
        Exclude annotation = fieldAttributes.getAnnotation(Exclude.class);
        return annotation != null && annotation.serialize();
    }

    @Override
    public boolean shouldSkipClass(Class<?> aClass) {
        return false;
    }
}
