package gov.gsa.dcoi.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Actual validator for the decimal min - allows empty strings and nulls
 */
public class DcoiDecimalMinValidator implements ConstraintValidator<DcoiDecimalMin, String> {

	private Double decimalMinValue;

	@Override
	public void initialize(DcoiDecimalMin param) {
		this.decimalMinValue = param.value();
	}

	@Override
	public boolean isValid(String dcoiDecimalMin, ConstraintValidatorContext ctx) {
		if (dcoiDecimalMin == null || dcoiDecimalMin.isEmpty()) {
			return true;
		} else {
			try {
				Double tempVal = Double.valueOf(dcoiDecimalMin);
				if (tempVal < this.decimalMinValue) {
					return false;
				}
				return true;

			} catch (NumberFormatException nfe) {
				return false;
			}
		}
	}

}
