package com.mastercard.user.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.util.CollectionUtils;

import com.mastercard.user.constant.UserServiceConstant;
import com.mastercard.user.entity.UserEntity;
import com.mastercard.user.exception.UserException;
import com.mastercard.user.model.User;

/**
 * This mapper class is used to map User model class to UserEntity class.
 *
 */
public final class UserMapper {

	public static UserEntity mapFromUserToEntity(final User user) {
		if (user == null) {
			throw new UserException(UserServiceConstant.ERROR_MSG_USER_IS_NULL);
		}
		final UserEntity userEntity = new UserEntity();
		userEntity.setId(user.getId());
		userEntity.setFirstName(user.getFirstName());
		userEntity.setLastName(user.getLastName());
		userEntity.setMiddleInitial(user.getMiddleInitial());
		userEntity.setEmail(user.getEmail());
		userEntity.setPhone(user.getPhone());
		userEntity.setBillingAddress(user.getBillingAddress());
		userEntity.setShippingAddress(user.getShippingAddress());

		return userEntity;
	}

	public static List<User> mapUserEntityListToUser(final List<UserEntity> userEntities) {

		if (CollectionUtils.isEmpty(userEntities)) {
			throw new UserException(UserServiceConstant.ERROR_MSG_NO_USER_FOUND);
		}
		final List<User> users = new ArrayList<>();
		for (final UserEntity userEntity : userEntities) {
			final User user = new User();
			user.setId(userEntity.getId());
			user.setFirstName(userEntity.getFirstName());
			user.setLastName(userEntity.getLastName());
			user.setMiddleInitial(userEntity.getMiddleInitial());
			user.setEmail(userEntity.getEmail());
			user.setPhone(userEntity.getPhone());
			user.setBillingAddress(userEntity.getBillingAddress());
			user.setShippingAddress(userEntity.getShippingAddress());
			users.add(user);
		}
		return users;
	}

	public static User mapUserEntityToUser(final Optional<UserEntity> optionalUserEntity, final String userId) {

		final UserEntity userEntity = optionalUserEntity
				.orElseThrow(() -> new UserException(UserServiceConstant.ERROR_MSG_USER_NOT_FOUND + userId));

		final User user = new User();
		user.setId(userEntity.getId());
		user.setFirstName(userEntity.getFirstName());
		user.setLastName(userEntity.getLastName());
		user.setMiddleInitial(userEntity.getMiddleInitial());
		user.setEmail(userEntity.getEmail());
		user.setPhone(userEntity.getPhone());
		user.setBillingAddress(userEntity.getBillingAddress());
		user.setShippingAddress(userEntity.getShippingAddress());
		return user;
	}
}
