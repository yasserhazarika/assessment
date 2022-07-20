package com.mastercard.user.service;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.mastercard.user.config.UserConfigProperties;
import com.mastercard.user.constant.UserServiceConstant;
import com.mastercard.user.exception.UserException;
import com.mastercard.user.model.User;

/**
 * This class is used to retrieve user information from properties. This class
 * will be loaded only with profile as Dev.
 */
@Service
@Profile("dev")
public class UserServiceDev implements UserService {

	private final UserConfigProperties userConfigProperties;

	public UserServiceDev(UserConfigProperties userConfigProperties) {
		this.userConfigProperties = userConfigProperties;
	}

	@Override
	public List<User> getUsers() {
		return userConfigProperties.getUsers();
	}

	@Override
	public User getUserDetails(final String userId) {
		if (StringUtils.isBlank(userId)) {
			throw new UserException(UserServiceConstant.ERROR_MSG_NULL_USER_ID);
		}
		final Optional<User> users = userConfigProperties.getUsers().stream()
				.filter(user -> userId.equals(user.getId())).findAny();
		return users.orElseThrow(() -> new UserException(UserServiceConstant.ERROR_MSG_USER_NOT_FOUND + userId));
	}

	@Override
	public String save(final User user) {
		return UserServiceConstant.USER_CREATED_MESSAGE;
	}

}
