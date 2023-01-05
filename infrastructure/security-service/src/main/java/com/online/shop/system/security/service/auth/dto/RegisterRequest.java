package com.online.shop.system.security.service.auth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@Builder
@RequiredArgsConstructor
public class RegisterRequest {
    private final UUID userID;
    private final String email;
    private final String password;
}
