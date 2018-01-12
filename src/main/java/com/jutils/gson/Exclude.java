package com.jutils.gson;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by mi on 18-1-12.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Exclude {
    public boolean serialize() default true;        // serialize 时 exclude

    public boolean deserialize() default true;      // deserialize 时 exclude
}
