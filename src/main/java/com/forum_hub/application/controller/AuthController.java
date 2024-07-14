package com.forum_hub.application.controller;

import javax.security.sasl.AuthenticationException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forum_hub.application.dto.AuthResponseDTO;
import com.forum_hub.application.dto.LoginRequestDTO;
import com.forum_hub.application.dto.RegisterRequestDTO;
import com.forum_hub.application.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("/login")
  public ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid LoginRequestDTO body) throws AuthenticationException {
    AuthResponseDTO response = authService.login(body);
    return ResponseEntity.ok(response);
  }

  @PostMapping("/register")
  public ResponseEntity<AuthResponseDTO> register(@RequestBody @Valid RegisterRequestDTO body) {
    AuthResponseDTO response = authService.register(body);
    return ResponseEntity.ok(response);
  }
}
