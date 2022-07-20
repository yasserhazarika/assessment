package com.mastercard.user.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.mastercard.user.model.User;

@Configuration
@ConfigurationProperties(prefix = "userlist")
public class UserConfigProperties {

	private List<User> users;

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}
