package kr.or.ddit.validate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;

public class CommonValidator<T> {
	private Validator validator;
	{
		ValidatorFactory factory = Validation.byDefaultProvider()
				.configure()
				.messageInterpolator(
					new ResourceBundleMessageInterpolator(
						new PlatformResourceBundleLocator("kr.or.ddit.msg.message")
					)	
				).buildValidatorFactory();
		validator = factory.getValidator();
	}
	
	public boolean validate(T validateTarget, Map<String, List<String>> errors, Class<?>...groups) {
		Set<ConstraintViolation<T>> violationSet = validator.validate(validateTarget, groups);
		if(violationSet.size()>0) {
			for(ConstraintViolation<T> violation : violationSet) {
				String propName = violation.getPropertyPath().toString();
				String message = violation.getMessage();
				List<String> msgList = errors.get(propName);
				if(msgList==null) {
					msgList = new ArrayList<>();
					errors.put(propName, msgList);
				}
				msgList.add(message);
			}
		}
		return violationSet.size()==0;
	}
}















