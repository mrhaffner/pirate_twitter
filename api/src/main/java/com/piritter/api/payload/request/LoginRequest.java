package com.piritter.api.payload.request;

import javax.validation.constraints.NotBlank;

import lombok.Getter;

@Getter
public class LoginRequest {

	@NotBlank
  	private String username;

	@NotBlank
	private String password;

	public LoginRequest(String username, String password) {
		this.username = username.toLowerCase();
		this.password = password;
  }
}
