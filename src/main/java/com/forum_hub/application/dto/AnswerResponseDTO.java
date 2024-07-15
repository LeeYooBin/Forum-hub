package com.forum_hub.application.dto;

import java.util.Date;

import com.forum_hub.domain.model.Answer;

public record AnswerResponseDTO(
  Long id,
  String message,
  String solution,
  Date createdAt
) {
  public AnswerResponseDTO(Answer answer) {
    this(
      answer.getId(),
      answer.getMessage(),
      answer.getSolution(),
      answer.getCreatedAt()
    );
  }
}
