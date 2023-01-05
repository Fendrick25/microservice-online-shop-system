package com.online.shop.system.security.service.auth.service;


import com.online.shop.system.security.service.auth.dto.AuthenticationRequest;
import com.online.shop.system.security.service.auth.dto.AuthenticationResponse;
import com.online.shop.system.security.service.auth.dto.RegisterRequest;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
