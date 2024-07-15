package com.forum_hub.application.service;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.forum_hub.application.dto.AuthResponseDTO;
import com.forum_hub.application.dto.LoginRequestDTO;
import com.forum_hub.application.dto.RegisterRequestDTO;
import com.forum_hub.domain.model.User;
import com.forum_hub.infra.repository.UserRepository;
import com.forum_hub.infra.service.TokenService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class AuthService {
  @Autowired
  private final UserRepository userRepository;

  @Autowired
  private final PasswordEncoder passwordEncoder;

  @Autowired
  private final TokenService tokenService;

  public AuthService(
    UserRepository userRepository,
    PasswordEncoder passwordEncoder,
    TokenService tokenService
  ) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.tokenService = tokenService;
  }

  @Transactional
  public AuthResponseDTO login(@Valid LoginRequestDTO dto) throws AuthenticationException {
    User user = userRepository.findByEmail(dto.email())
      .orElseThrow(() -> new EntityNotFoundException("User not found."));

    if (passwordEncoder.matches(dto.password(), user.getPassword())) {
      String token = this.tokenService.generateToken(user);
      return new AuthResponseDTO(user, token);
    } else {
      throw new AuthenticationException("Invalid password.");
    }
  }

  @Transactional
  public AuthResponseDTO register(@Valid RegisterRequestDTO dto) {
    User newUser = new User(
      dto.name(),
      dto.email(),
      passwordEncoder.encode(dto.password())
    );
    userRepository.save(newUser);
    String token = tokenService.generateToken(newUser);
    return new AuthResponseDTO(newUser, token);
  }
}
