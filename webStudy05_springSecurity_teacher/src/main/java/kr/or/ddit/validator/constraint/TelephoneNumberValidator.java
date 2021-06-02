package kr.or.ddit.validator.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

public class TelephoneNumberValidator implements 
				ConstraintValidator<TelephoneNumber, String>{
	private TelephoneNumber annotation;
	@Override
	public void initialize(TelephoneNumber constraintAnnotation) {
		this.annotation = constraintAnnotation;
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(StringUtils.isBlank(value)) return true;
		String regexp = annotation.regexp();
		return value.matches(regexp);
	}

}









