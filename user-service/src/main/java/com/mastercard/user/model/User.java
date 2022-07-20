package com.mastercard.user.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.mastercard.user.validator.ValidPhoneNumber;

public class User {

	private static final String PATTERN_ALPHA = "^[a-zA-Z]*$";

	private static final String ERROR_MESSAGE_PATTERN = " Accepted chars: a to z and A to Z";

	private String id;

	@NotNull(message = "First Name is mandatory")
	@Size(max = 20, message = "Max size for firstName is 20")
	@Pattern(regexp = PATTERN_ALPHA, message = "firstName should match the pattern :" + ERROR_MESSAGE_PATTERN)
	private String firstName;

	@NotNull(message = "Last Name is mandatory")
	@Size(max = 20, message = "Max size for lastName is 20")
	@Pattern(regexp = PATTERN_ALPHA, message = "lastName should match the pattern :" + ERROR_MESSAGE_PATTERN)
	private String lastName;

	@Size(max = 1, message = "Max size for middleInitial is 1")
	@Pattern(regexp = PATTERN_ALPHA, message = "middleInitial should match the pattern :" + ERROR_MESSAGE_PATTERN)
	private String middleInitial;

	@Email(message = "Email should be valid")
	@Size(max = 50, message = "Max size for email is 50")
	private String email;

	@NotNull(message = "Phone number is mandatory.")
	@ValidPhoneNumber(message = "Phone Number should have 10 digit.")
	private String phone;

	@NotNull(message = "Billing Address is mandatory")
	private String billingAddress;

	private String shippingAddress;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleInitial() {
		return middleInitial;
	}

	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", middleInitial="
				+ middleInitial + ", email=" + email + ", phone=" + phone + ", billingAddress=" + billingAddress
				+ ", shippingAddress=" + shippingAddress + "]";
	}

}
