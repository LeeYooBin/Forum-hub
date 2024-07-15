package com.forum_hub.application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.forum_hub.application.dto.AnswerRequestDTO;
import com.forum_hub.application.dto.AnswerResponseDTO;
import com.forum_hub.application.service.AnswerService;
import com.forum_hub.domain.model.User;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/answers")
public class AnswerController {
  private final AnswerService answerService;

  public AnswerController(AnswerService answerService) {
    this.answerService = answerService;
  }

  @PostMapping
  public ResponseEntity<AnswerResponseDTO> createAnswer(@RequestBody @Valid AnswerRequestDTO body, Authentication authentication) {
    User user = (User) authentication.getPrincipal();
    AnswerResponseDTO response = answerService.createAnswer(body, user.getId());
    return ResponseEntity.ok(response);
  }

  @GetMapping("/topic/{topicId}")
  public ResponseEntity<List<AnswerResponseDTO>> listAnswers(@PathVariable Long topicId) {
    List<AnswerResponseDTO> answers = answerService.listAnswers(topicId);
    return ResponseEntity.ok(answers);
  }

  @GetMapping("/{id}")
  public ResponseEntity<AnswerResponseDTO> getAnswer(@PathVariable Long id) {
    AnswerResponseDTO response = answerService.getAnswer(id);
    return ResponseEntity.ok(response);
  }

  @PutMapping("/{id}")
  public ResponseEntity<AnswerResponseDTO> updateAnswer(@PathVariable Long id, @RequestBody @Valid AnswerRequestDTO body) {
    AnswerResponseDTO response = answerService.updateAnswer(id, body);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteAnswer(@PathVariable Long id) {
    answerService.deleteAnswer(id);
    return ResponseEntity.noContent().build();
  }
}
