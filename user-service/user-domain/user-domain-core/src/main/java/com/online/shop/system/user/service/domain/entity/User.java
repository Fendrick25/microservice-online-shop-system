package com.online.shop.system.user.service.domain.entity;

import com.online.shop.system.user.service.domain.valueobject.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class User {

    private UUID id;
    private final String username;
    private final String name;
    private final String email;
    private final String password;
    private List<Address> address;
    private ZonedDateTime createdAt;

    public void initializeUser(){
        id = UUID.randomUUID();
        createdAt = ZonedDateTime.now(ZoneId.of("UTC"));
    }
}
