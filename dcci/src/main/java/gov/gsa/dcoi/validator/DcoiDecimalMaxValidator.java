package gov.gsa.dcoi.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator for the Decimal Max - allows nulls and empty strings
 */
public class DcoiDecimalMaxValidator implements ConstraintValidator<DcoiDecimalMax, String> {

	private Double decimalMaxValue;

	@Override
	public void initialize(DcoiDecimalMax param) {
		this.decimalMaxValue = param.value();
	}

	@Override
	public boolean isValid(String dcoiDecimalMax, ConstraintValidatorContext ctx) {
		if (dcoiDecimalMax == null || dcoiDecimalMax.isEmpty()) {
			return true;
		} else {
			try {
				Double tempInt = Double.valueOf(dcoiDecimalMax);
				if (tempInt > this.decimalMaxValue) {
					return false;
				}
				return true;

			} catch (NumberFormatException nfe) {
				return false;
			}
		}
	}

}
