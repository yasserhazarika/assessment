package com.mastercard.mask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mastercard.mask.model.User;
import com.mastercard.mask.service.UserMaskingService;

@RestController
@RequestMapping("/mask")
public class UserMaskController {

	@Autowired
	private UserMaskingService userMaskingService;

	@PostMapping(value = "/user")
	public ResponseEntity<String> mask(final @RequestBody User user) {
		return new ResponseEntity<>(userMaskingService.maskUser(user), HttpStatus.CREATED);
	}
}
