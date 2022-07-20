package com.mastercard.user.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * This class is used to validate phone number.
 */
public class PhoneNumberValidator implements ConstraintValidator<ValidPhoneNumber, String> {

	private static final Pattern PATTERN_PHONE_REGEX = Pattern.compile("^\\d{10}$");

	@Override
	public boolean isValid(final String value, final ConstraintValidatorContext context) {
		return PATTERN_PHONE_REGEX.matcher(value).matches();
	}
}
