package com.forum_hub.application.dto;

import com.forum_hub.domain.model.User;

public record AuthResponseDTO(User user, String token) {}
