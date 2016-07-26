package utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/*
 * Page-131 JSR-303 custom annotation and validator (1/3)
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EanValidator.class)
@play.data.Form.Display(name = "constraint.ean", attributes = { "value" })
public @interface EAN {
	/* This method uses the EanValidator.java's default message. */
	String message() default EanValidator.message;

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}