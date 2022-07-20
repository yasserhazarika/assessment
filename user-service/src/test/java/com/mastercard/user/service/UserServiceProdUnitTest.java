package com.mastercard.user.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mastercard.user.constant.UserServiceConstant;
import com.mastercard.user.entity.UserEntity;
import com.mastercard.user.exception.UserException;
import com.mastercard.user.model.User;
import com.mastercard.user.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceProdUnitTest {

	private static final String USER_ID = "232423423";

	@InjectMocks
	private UserServiceProd userServiceProd;

	@Mock
	private UserRepository userRepository;

	@Test
	void testGetUsersWhenNoUserFound() {
		// given
		when(userRepository.findAll()).thenReturn(new ArrayList<>());

		// when
		final UserException userException = Assertions.assertThrows(UserException.class,
				() -> userServiceProd.getUsers());

		// then
		Assertions.assertEquals(UserServiceConstant.ERROR_MSG_NO_USER_FOUND, userException.getMessage());
	}

	@Test
	void testGetUsers() {
		// given
		final List<UserEntity> userEntities = new ArrayList<>();
		userEntities.add(getUserEntity());
		when(userRepository.findAll()).thenReturn(userEntities);

		// when
		final List<User> users = userServiceProd.getUsers();

		// then
		Assertions.assertNotNull(users);

		verify(userRepository, times(1)).findAll();
	}

	@Test
	void testgetUserDetailsWhenUserIdIsNull() {
		// given
		final UserException userException = Assertions.assertThrows(UserException.class,
				() -> userServiceProd.getUserDetails(null));

		// then
		Assertions.assertEquals(UserServiceConstant.ERROR_MSG_NULL_USER_ID, userException.getMessage());
	}

	@Test
	void testgetUserDetailsWhenUserIdNotFound() {
		// given
		when(userRepository.findById(USER_ID)).thenReturn(Optional.empty());
		final UserException userException = Assertions.assertThrows(UserException.class,
				() -> userServiceProd.getUserDetails(USER_ID));

		// then
		Assertions.assertEquals(UserServiceConstant.ERROR_MSG_USER_NOT_FOUND + USER_ID, userException.getMessage());
	}

	@Test
	void testGetUserDetails() {
		// given
		final UserEntity userEntity = getUserEntity();
		when(userRepository.findById(USER_ID)).thenReturn(Optional.of(userEntity));

		// when
		final User user = userServiceProd.getUserDetails(USER_ID);

		// then
		Assertions.assertEquals(userEntity.getId(), user.getId());
		Assertions.assertEquals(userEntity.getFirstName(), user.getFirstName());
		Assertions.assertEquals(userEntity.getMiddleInitial(), user.getMiddleInitial());
		Assertions.assertEquals(userEntity.getLastName(), user.getLastName());
		Assertions.assertEquals(userEntity.getPhone(), user.getPhone());
		Assertions.assertEquals(userEntity.getEmail(), user.getEmail());
		Assertions.assertEquals(userEntity.getBillingAddress(), user.getBillingAddress());
		Assertions.assertEquals(userEntity.getShippingAddress(), user.getShippingAddress());

		verify(userRepository, times(1)).findById(Mockito.any());

	}

	@Test
	void testSaveWhenUserNotSet() {
		// when
		final UserException userException = Assertions.assertThrows(UserException.class,
				() -> userServiceProd.save(null));

		// then
		Assertions.assertEquals(UserServiceConstant.ERROR_MSG_USER_IS_NULL, userException.getMessage());

	}

	@Test
	void testSave() {

		// when
		final String resultmessage = userServiceProd.save(new User());

		// then
		Assertions.assertEquals(UserServiceConstant.USER_CREATED_MESSAGE, resultmessage);

		verify(userRepository, times(1)).save(Mockito.any());

	}

	private UserEntity getUserEntity() {
		final UserEntity userEntity = new UserEntity();
		userEntity.setId(USER_ID);
		userEntity.setFirstName("john");
		userEntity.setMiddleInitial("k");
		userEntity.setLastName("doe");
		userEntity.setPhone("44409987765");
		userEntity.setEmail("john.doe@mastercard.com");
		userEntity.setBillingAddress("15209 Morris Road, Unit 44567, Dunwoody 30338");
		userEntity.setShippingAddress("Westside ParkWay, Alpharetta 30009");
		return userEntity;
	}
}
