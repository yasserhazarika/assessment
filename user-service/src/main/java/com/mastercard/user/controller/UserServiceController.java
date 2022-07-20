package com.mastercard.user.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mastercard.user.connect.UserServiceConnector;
import com.mastercard.user.model.User;
import com.mastercard.user.service.UserService;

/**
 * This controller class is used to process user input.
 */
@RestController
@RequestMapping("/userservice")
public class UserServiceController {

	private static final Logger log = LogManager.getLogger(UserServiceController.class);

	private final UserService userService;

	private final UserServiceConnector userServiceConnector;

	public UserServiceController(final UserService userService, final UserServiceConnector userServiceConnector) {
		this.userService = userService;
		this.userServiceConnector = userServiceConnector;
	}

	@GetMapping(value = "/users")
	public ResponseEntity<List<User>> getUsers() {
		return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
	}

	@GetMapping(value = "/user/{id}")
	public ResponseEntity<User> getUserDetails(final @PathVariable("id") String userId) {
		final User user = userService.getUserDetails(userId);
		log.info("Retrieved user: " + userServiceConnector.maskMessage(user));
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@PostMapping(value = "/user")
	public ResponseEntity<String> save(final @Valid @RequestBody User user) {
		user.setId(UUID.randomUUID().toString());
		log.info("User saved: " + userServiceConnector.maskMessage(user));
		return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
	}
}
