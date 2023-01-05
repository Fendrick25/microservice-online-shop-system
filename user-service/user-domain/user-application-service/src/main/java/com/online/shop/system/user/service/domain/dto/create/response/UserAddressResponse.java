package com.online.shop.system.user.service.domain.dto.create.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class UserAddressResponse {
    @NotNull
    private final UUID userID;
    @NotNull
    private final Integer addressID;
}
