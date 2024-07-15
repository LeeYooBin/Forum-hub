package com.forum_hub.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequestDTO(
  @NotBlank String name, 
  @Email @NotBlank String email, 
  @NotBlank String password
) {}
