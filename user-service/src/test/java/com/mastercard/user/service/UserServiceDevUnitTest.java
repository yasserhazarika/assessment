package com.mastercard.user.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mastercard.user.config.UserConfigProperties;
import com.mastercard.user.constant.UserServiceConstant;
import com.mastercard.user.exception.UserException;
import com.mastercard.user.model.User;

@ExtendWith(MockitoExtension.class)
class UserServiceDevUnitTest {

	private static final String USER_ID = "232423423";

	@InjectMocks
	private UserServiceDev userServiceDev;

	@Mock
	private UserConfigProperties userConfigProperties;

	@Test
	void testGetUsers() {
		// given
		when(userConfigProperties.getUsers()).thenReturn(List.of(getUser()));

		// when
		final List<User> users = userServiceDev.getUsers();

		// then
		Assertions.assertNotNull(users);
		verify(userConfigProperties, times(1)).getUsers();
	}

	@Test
	void testgetUserDetailsWhenUserIdIsNull() {
		// given
		final UserException userException = Assertions.assertThrows(UserException.class,
				() -> userServiceDev.getUserDetails(null));

		// then
		Assertions.assertEquals(UserServiceConstant.ERROR_MSG_NULL_USER_ID, userException.getMessage());
	}

	@Test
	void testgetUserDetailsWhenUserIdNotFound() {
		// given
		when(userConfigProperties.getUsers()).thenReturn(List.of(getUser()));
		final String userId = "778994554";

		// when
		final UserException userException = Assertions.assertThrows(UserException.class,
				() -> userServiceDev.getUserDetails(userId));

		// then
		Assertions.assertEquals(UserServiceConstant.ERROR_MSG_USER_NOT_FOUND + userId, userException.getMessage());
	}

	@Test
	void testGetUserDetails() {
		// given
		final User user = getUser();
		when(userConfigProperties.getUsers()).thenReturn(List.of(user));

		// when
		final User userResponse = userServiceDev.getUserDetails(USER_ID);

		// then
		Assertions.assertEquals(user.getId(), userResponse.getId());
		Assertions.assertEquals(user.getFirstName(), userResponse.getFirstName());
		Assertions.assertEquals(user.getMiddleInitial(), userResponse.getMiddleInitial());
		Assertions.assertEquals(user.getLastName(), userResponse.getLastName());
		Assertions.assertEquals(user.getPhone(), userResponse.getPhone());
		Assertions.assertEquals(user.getEmail(), userResponse.getEmail());
		Assertions.assertEquals(user.getBillingAddress(), userResponse.getBillingAddress());
		Assertions.assertEquals(user.getShippingAddress(), userResponse.getShippingAddress());

		verify(userConfigProperties, times(1)).getUsers();

	}

	@Test
	void testSave() {

		// when
		final String resultmessage = userServiceDev.save(new User());

		// then
		Assertions.assertEquals(UserServiceConstant.USER_CREATED_MESSAGE, resultmessage);

	}

	private User getUser() {
		final User user = new User();
		user.setId(USER_ID);
		user.setFirstName("john");
		user.setMiddleInitial("k");
		user.setLastName("doe");
		user.setPhone("44409987765");
		user.setEmail("john.doe@mastercard.com");
		user.setBillingAddress("15209 Morris Road, Unit 44567, Dunwoody 30338");
		user.setShippingAddress("Westside ParkWay, Alpharetta 30009");
		return user;
	}
}
