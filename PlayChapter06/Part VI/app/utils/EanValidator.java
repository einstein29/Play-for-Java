package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;

import play.data.validation.Constraints;
import play.libs.F.Tuple;

/*
 * Page-131 JSR-303 custom annotation and validator (2/3)
 * (1) Constraints.Validator<?> is from Play framework.
 * (2) ConstraintValidator is from Java which implements the JSR-303.
 */
public class EanValidator extends Constraints.Validator<String> implements
		ConstraintValidator<EAN, String> {

	final static public String message = "This is the default message: error.invalid.ean";

	public EanValidator() {
	}

	@Override
	public void initialize(EAN arg0) {
	}

	@Override
	public boolean isValid(String object) {
		final String pattern = "^[0-9]{13}$";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(object);
		return m.matches();
	}

	@Override
	public Tuple<String, Object[]> getErrorMessageKey() {
		final String errorMessage = "error.invalid.EAN.number";
		Object[] object = new Object[] {};
		Tuple<String, Object[]> tuple = new Tuple<String, Object[]>(
				errorMessage, object);
		return tuple;
	}

}
