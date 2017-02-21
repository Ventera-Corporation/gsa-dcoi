package gov.gsa.dcoi.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Custom Validator for integer values, allows empty strings and nulls
 */
public class DcoiMinValidator implements ConstraintValidator<DcoiMin, String> {

	private Integer minValue;

	@Override
	public void initialize(DcoiMin param) {
		this.minValue = param.value();
	}

	@Override
	public boolean isValid(String dcoiMin, ConstraintValidatorContext ctx) {
		if (dcoiMin == null || dcoiMin.isEmpty()) {
			return true;
		} else {
			try {
				Integer tempInt = Integer.valueOf(dcoiMin);
				if (tempInt < this.minValue) {
					return false;
				}
				return true;

			} catch (NumberFormatException nfe) {
				return false;
			}
		}
	}

}
