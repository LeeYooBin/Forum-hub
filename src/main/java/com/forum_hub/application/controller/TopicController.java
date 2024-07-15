package com.forum_hub.application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.forum_hub.application.dto.TopicRequestDTO;
import com.forum_hub.application.dto.TopicResponseDTO;
import com.forum_hub.application.service.TopicService;
import com.forum_hub.domain.enums.EnumTopicStatus;
import com.forum_hub.domain.model.User;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/topics")
public class TopicController {

  private final TopicService topicService;

  public TopicController(TopicService topicService) {
    this.topicService = topicService;
  }

  @PostMapping
  public ResponseEntity<TopicResponseDTO> createTopic(@RequestBody @Valid TopicRequestDTO body, Authentication authentication) {
    User user = (User) authentication.getPrincipal();
    TopicResponseDTO response = topicService.createTopic(body, user.getId());
    return ResponseEntity.ok(response);
  }

  @GetMapping
  public ResponseEntity<List<TopicResponseDTO>> listTopics(Authentication authentication) {
    User user = (User) authentication.getPrincipal();
    List<TopicResponseDTO> topics = topicService.listTopics(user.getId());
    return ResponseEntity.ok(topics);
  }

  @GetMapping("/{id}")
  public ResponseEntity<TopicResponseDTO> getTopic(@PathVariable Long id) {
    TopicResponseDTO response = topicService.getTopic(id);
    return ResponseEntity.ok(response);
  }

  @PutMapping("/{id}")
  public ResponseEntity<TopicResponseDTO> updateTopic(@PathVariable Long id, @RequestBody @Valid TopicRequestDTO body) {
    TopicResponseDTO response = topicService.updateTopic(id, body);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {
    topicService.deleteTopic(id);
    return ResponseEntity.noContent().build();
  }

  @PatchMapping("/{id}/status")
  public ResponseEntity<TopicResponseDTO> updateTopicStatus(
    @PathVariable Long id,
    @RequestParam EnumTopicStatus status
  ) {
    TopicResponseDTO response = topicService.updateTopicStatus(id, status);
    return ResponseEntity.ok(response);
  }
}
