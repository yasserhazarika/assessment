package com.mastercard.mask.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.mastercard.mask.controller.UserMaskController;
import com.mastercard.mask.model.User;

@Service
public class UserMaskingService {

	private static final Logger log = LogManager.getLogger(UserMaskController.class);

	private static final String EMAIL_PATTERN = "(?<=.{1}).(?=.*@)";

	private static final String LAST_NAME_PATTERN = "(?<=.{1}).(?=.*)";

	private static final String PHONE_NUMBER_PATTERN = "\\d(?=(?:\\D*\\d){4})";

	public String maskUser(final User user) {
		log.info("Begin to mask User details.");
		user.setLastName(user.getLastName().replaceAll(LAST_NAME_PATTERN, "*"));
		user.setEmail(user.getEmail().replaceAll(EMAIL_PATTERN, "*"));
		user.setPhone(user.getPhone().replaceAll(PHONE_NUMBER_PATTERN, "*"));
		final String maskedUser = user.toString();
		log.info("Masked User details: " + maskedUser);
		return maskedUser;
	}
}
