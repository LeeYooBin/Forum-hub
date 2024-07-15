package com.forum_hub.application.dto;

import java.util.Date;

import com.forum_hub.domain.model.Topic;

public record TopicResponseDTO(
  Long id,
  String title,
  String message,
  String status,
  Long courseId,
  Long authorId,
  Date createdAt
) {
  public TopicResponseDTO(Topic topic) {
    this(topic.getId(), 
    topic.getTitle(), 
    topic.getMessage(), 
    topic.getStatus().name(), 
    topic.getCourse().getId(), 
    topic.getAuthor().getId(), 
    topic.getCreatedAt());
  }
}