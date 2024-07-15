package com.forum_hub.infra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.forum_hub.domain.model.Topic;

import com.forum_hub.domain.model.User;

public interface TopicRepository extends JpaRepository<Topic, Long> {
  List<Topic> findByAuthor(User author);
}
