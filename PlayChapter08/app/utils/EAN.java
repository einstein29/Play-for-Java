package utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EanValidator.class)
@play.data.Form.Display(name = "constraint.ean", attributes = { "value" })
public @interface EAN {
	String message() default EanValidator.message;

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}