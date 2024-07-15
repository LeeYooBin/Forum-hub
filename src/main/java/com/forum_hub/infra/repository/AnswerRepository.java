package com.forum_hub.infra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.forum_hub.domain.model.Answer;
import com.forum_hub.domain.model.Topic;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
  List<Answer> findByTopic(Topic topic);
}
