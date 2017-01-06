package gov.gsa.dcoi.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Custom decimal max validator for DCOI
 */
@Documented
@Constraint(validatedBy = DcoiDecimalMaxValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface DcoiDecimalMax {

	String message() default "{DcoiDecimalMax}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	double value();

}
