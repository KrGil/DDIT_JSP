package kr.or.ddit.validate.rule;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TelNumberValidator implements ConstraintValidator<TelNumber, String>{
	
	private TelNumber telNumber;
	
	@Override
	public void initialize(TelNumber constraintAnnotation) {
		this.telNumber = constraintAnnotation;
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		boolean valid = true;
		if(value!=null && !value.isEmpty()) {
			valid = value.matches(telNumber.value());
		}
		return valid;
	}

}












