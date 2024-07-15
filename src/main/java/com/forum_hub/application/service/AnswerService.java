package com.forum_hub.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.forum_hub.application.dto.AnswerRequestDTO;
import com.forum_hub.application.dto.AnswerResponseDTO;
import com.forum_hub.domain.model.Answer;
import com.forum_hub.domain.model.User;
import com.forum_hub.domain.model.Topic;
import com.forum_hub.infra.repository.AnswerRepository;
import com.forum_hub.infra.repository.TopicRepository;
import com.forum_hub.infra.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

public class AnswerService {
  @Autowired
  private final AnswerRepository answerRepository;

  @Autowired
  private final TopicRepository topicRepository;

  @Autowired
  private final UserRepository userRepository;

  public AnswerService(
    AnswerRepository answerRepository,
    TopicRepository topicRepository,
    UserRepository userRepository
  ) {
    this.answerRepository = answerRepository;
    this.topicRepository = topicRepository;
    this.userRepository = userRepository;
  }

  @Transactional
  public AnswerResponseDTO createAnswer(AnswerRequestDTO dto, Long userId) {
    User author = userRepository.findById(userId)
      .orElseThrow(() -> new EntityNotFoundException("Author not found."));
    Topic topic = topicRepository.findById(dto.topicId())
      .orElseThrow(() -> new EntityNotFoundException("Topic not found."));
    
    Answer answer = new Answer(dto.message(), dto.solution(), topic, author);
    answerRepository.save(answer);

    return new AnswerResponseDTO(answer);
  }

  public List<AnswerResponseDTO> listAnswers(Long topicId) {
    Topic topic = topicRepository.findById(topicId)
      .orElseThrow(() -> new EntityNotFoundException("Topic not found."));
    
    return answerRepository.findByTopic(topic).stream()
      .map(AnswerResponseDTO::new)
      .toList();
  }

  public AnswerResponseDTO getAnswer(Long id) {
    Answer answer = answerRepository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Answer not found."));
    
    return new AnswerResponseDTO(answer);
  }

  @Transactional
  public AnswerResponseDTO updateAnswer(Long id, AnswerRequestDTO dto) {
    Answer answer = answerRepository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Answer not found."));
    
    answer.setMessage(dto.message());
    answer.setSolution(dto.solution());
    answerRepository.save(answer);
    return new AnswerResponseDTO(answer);
  }

  @Transactional
  public void deleteAnswer(Long id) {
    Answer answer = answerRepository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Answer not found."));
    answerRepository.delete(answer);
  }
}
