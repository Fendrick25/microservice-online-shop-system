package com.online.shop.system.user.service.domain.dto.create.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import jakarta.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
public class AddressIDResponse {

    @NotNull
    private final Integer addressID;
}
