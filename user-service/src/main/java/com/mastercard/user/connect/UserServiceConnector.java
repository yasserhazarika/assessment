package com.mastercard.user.connect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.mastercard.user.model.User;

/**
 * This class is used to call external service using rest template.
 */
@Component
public class UserServiceConnector {

	@Value("${maskservice.maskuser.url}")
	private String maskUrl;

	private final RestTemplate restTemplate;

	private static final Logger log = LogManager.getLogger(UserServiceConnector.class);

	public UserServiceConnector(final RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	/**
	 * This method is used to call the masking service and mask the following
	 * personal field information.
	 * 
	 * @param user of the type {@link User}
	 * @return of the type {@link String}
	 */
	public String maskMessage(final User user) {
		log.info("Requesting Mask User Information from url: " + maskUrl);
		final String message = restTemplate.postForObject(maskUrl, user, String.class);
		log.info("Succesfully received the mask Information " + message);
		return message;
	}
}
