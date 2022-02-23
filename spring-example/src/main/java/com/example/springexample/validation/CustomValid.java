package com.example.springexample.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MyValidator.class)
public @interface CustomValid {
    // 에러 메시지
    String message() default "Invalid path";

    // 보일러 플레이트 코드
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
