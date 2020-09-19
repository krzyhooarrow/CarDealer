package spring.repository_layer.triggers;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
public @interface Triggerable {
    String operationType() default "update";
}
