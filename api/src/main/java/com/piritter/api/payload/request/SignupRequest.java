package com.piritter.api.payload.request;

import java.util.Set;

import javax.validation.constraints.*;

import lombok.Data;

@Data
public class SignupRequest {
  
  @NotBlank
  @Size(min = 3, max = 20)
  private String username;

  private Set<String> role;  // should not have this, default is user

  @NotBlank
  @Size(min = 6, max = 40)
  private String password;
}
