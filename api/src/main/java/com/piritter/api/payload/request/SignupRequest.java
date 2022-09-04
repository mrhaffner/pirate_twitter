package com.piritter.api.payload.request;

import javax.validation.constraints.*;

import lombok.Getter;

@Getter
public class SignupRequest {
  
  @NotBlank
  @Pattern(regexp = "[a-zA-Z_0-9]+")
  @Size(min = 4, max = 15)
  private String username;

  @NotBlank
  @Size(min = 6, max = 40)
  private String password;

  public SignupRequest(String username, String password) {
    this.username = username.toLowerCase();
    this.password = password;
  }
}
