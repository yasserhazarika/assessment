package com.mastercard.user.service;

import java.util.List;

import com.mastercard.user.model.User;

public interface UserService {

	public List<User> getUsers();

	public User getUserDetails(final String userId);

	public String save(final User user);

}
