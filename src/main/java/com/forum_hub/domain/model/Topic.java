package com.forum_hub.domain.model;

import com.forum_hub.domain.enums.EnumTopicStatus;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "topics")
public class Topic {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String message;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private EnumTopicStatus status;

  @ManyToOne
  @JoinColumn(name = "course_id", nullable = false)
  private Course course;

  @ManyToOne
  @JoinColumn(name = "author_id", nullable = false)
  private User author;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created_at", nullable = false)
  private Date createdAt;

  @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
  private List<Answer> answers;

  public Topic(String title, String message, Course course, User author) {
    this.title = title;
    this.message = message;
    this.course = course;
    this.author = author;
    this.status = EnumTopicStatus.OPEN;
    this.createdAt = new Date();
    this.answers = new ArrayList<Answer>();
  }
}
