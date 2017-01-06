package gov.gsa.dcoi.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Custom validation class for Integers
 */
@Documented
@Constraint(validatedBy = DcoiMinValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface DcoiMin {

	String message() default "{DcoiMin}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	int value();

}
