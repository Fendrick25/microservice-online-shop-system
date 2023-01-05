package com.online.shop.system.user.service.domain.dto.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
public class CreateUser {

    @NotNull
    private final String username;
    @NotNull
    private final String name;
    @Email
    private final String email;
    @NotNull
    private final String password;
}
