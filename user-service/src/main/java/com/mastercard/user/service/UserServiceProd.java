package com.mastercard.user.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.mastercard.user.constant.UserServiceConstant;
import com.mastercard.user.entity.UserEntity;
import com.mastercard.user.exception.UserException;
import com.mastercard.user.mapper.UserMapper;
import com.mastercard.user.model.User;
import com.mastercard.user.repository.UserRepository;

/**
 * This class is used to retrieve and save user information from database(H2).
 * This class will be loaded only with profile as Prod.
 */
@Service
@Profile("prod")
public class UserServiceProd implements UserService {

	private final UserRepository userRepository;

	public UserServiceProd(final UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List<User> getUsers() {
		return UserMapper.mapUserEntityListToUser(userRepository.findAll());
	}

	@Override
	public User getUserDetails(final String userId) {
		if (StringUtils.isBlank(userId)) {
			throw new UserException(UserServiceConstant.ERROR_MSG_NULL_USER_ID);
		}
		return UserMapper.mapUserEntityToUser(userRepository.findById(userId), userId);
	}

	@Override
	public String save(final User user) {
		final UserEntity userEntity = UserMapper.mapFromUserToEntity(user);
		userRepository.save(userEntity);
		return UserServiceConstant.USER_CREATED_MESSAGE;
	}
}
