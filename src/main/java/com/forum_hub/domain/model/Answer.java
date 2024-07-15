package com.forum_hub.domain.model;

import java.util.Date;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "answers")
public class Answer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String message;

  @Column(nullable = false)
  private String solution;

  @ManyToOne
  @JoinColumn(name = "topic_id", nullable = false)
  private Topic topic;

  @ManyToOne
  @JoinColumn(name = "author_id", nullable = false)
  private User author;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created_at", nullable = false)
  private Date createdAt;

  public Answer(String message, String solution, Topic topic, User author) {
    this.message = message;
    this.solution = solution;
    this.topic = topic;
    this.author = author;
    this.createdAt = new Date();
  }
}
