package kr.or.ddit.validator.constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=TelephoneNumberValidator.class)
public @interface TelephoneNumber {
	String regexp() default "\\d{2,3}-\\d{3,4}-\\d{4}";
	String placeholder() default "000-0000-0000";
	String message() default "{kr.or.ddit.validator.constraint.TelephoneNumber.message}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default { };
}













