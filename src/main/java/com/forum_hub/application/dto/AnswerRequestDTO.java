package com.forum_hub.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AnswerRequestDTO(
  @NotBlank String message,
  @NotBlank String solution,
  @NotNull Long topicId
) {}
