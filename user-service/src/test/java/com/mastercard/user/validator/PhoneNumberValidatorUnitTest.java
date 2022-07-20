package com.mastercard.user.validator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PhoneNumberValidatorUnitTest {

	@InjectMocks
	private PhoneNumberValidator phoneNumberValidator;

	@Test
	void testPhoneNumber() {
		assertTrue(phoneNumberValidator.isValid("2055550125", null));
		assertFalse(phoneNumberValidator.isValid("20555501", null));
		assertFalse(phoneNumberValidator.isValid("2P55550125", null));
	}

}
