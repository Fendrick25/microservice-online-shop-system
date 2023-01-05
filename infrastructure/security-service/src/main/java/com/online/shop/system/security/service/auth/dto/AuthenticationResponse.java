package com.online.shop.system.security.service.auth.dto;

import lombok.*;

@Getter
@Builder
@RequiredArgsConstructor
public class AuthenticationResponse {
    private final String token;
}
