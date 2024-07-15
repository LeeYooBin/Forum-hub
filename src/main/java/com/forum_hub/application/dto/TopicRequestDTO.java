package com.forum_hub.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicRequestDTO(
  @NotBlank String title,
  @NotBlank String message,
  @NotNull Long courseId
) {}
