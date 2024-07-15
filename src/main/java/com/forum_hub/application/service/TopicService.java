package com.forum_hub.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forum_hub.application.dto.TopicRequestDTO;
import com.forum_hub.application.dto.TopicResponseDTO;
import com.forum_hub.domain.enums.EnumTopicStatus;
import com.forum_hub.domain.model.Course;
import com.forum_hub.domain.model.Topic;
import com.forum_hub.domain.model.User;
import com.forum_hub.infra.repository.CourseRepository;
import com.forum_hub.infra.repository.TopicRepository;
import com.forum_hub.infra.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class TopicService {
  @Autowired
  private TopicRepository topicRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private CourseRepository courseRepository;

  @Transactional
  public TopicResponseDTO createTopic(TopicRequestDTO dto, Long userId) {
    User author = userRepository.findById(userId)
      .orElseThrow(() -> new EntityNotFoundException("Author not found."));
    Course course = courseRepository.findById(dto.courseId())
      .orElseThrow(() -> new EntityNotFoundException("Course not found."));

    Topic topic = new Topic(dto.title(), dto.message(), course, author);
    topicRepository.save(topic);

    return new TopicResponseDTO(topic);
  }

  public List<TopicResponseDTO> listTopics(Long userId) {
    User user = userRepository.findById(userId)
      .orElseThrow(() -> new EntityNotFoundException("User not found."));
    return topicRepository.findByAuthor(user).stream()
      .map(TopicResponseDTO::new)
      .toList();
  }

  public TopicResponseDTO getTopic(Long id) {
    Topic topic = topicRepository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Topic not found."));
    return new TopicResponseDTO(topic);
  }

  @Transactional
  public TopicResponseDTO updateTopic(Long id, TopicRequestDTO dto) {
    Topic topic = topicRepository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Topic not found."));
    topic.setTitle(dto.title());
    topic.setMessage(dto.message());
    topicRepository.save(topic);
    return new TopicResponseDTO(topic);
  }

  @Transactional
  public void deleteTopic(Long id) {
    Topic topic = topicRepository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Topic not found."));
    topicRepository.delete(topic);
  }

  @Transactional
  public TopicResponseDTO updateTopicStatus(Long id, EnumTopicStatus newStatus) {
    Topic topic = topicRepository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Topic not found."));
    
    topic.setStatus(newStatus);
    topicRepository.save(topic);
    
    return new TopicResponseDTO(topic);
  }
}