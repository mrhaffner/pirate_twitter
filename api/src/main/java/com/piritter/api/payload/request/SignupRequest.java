package com.piritter.api.payload.request;

import javax.validation.constraints.*;

import lombok.Data;

@Data
public class SignupRequest {
  
  @NotBlank
  @Pattern(regexp = "[a-zA-Z_0-9]+")
  @Size(min = 4, max = 15)
  private String username;

  @NotBlank
  @Size(min = 6, max = 40)
  private String password;
}
