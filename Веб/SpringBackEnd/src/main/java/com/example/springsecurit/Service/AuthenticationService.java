package com.example.springsecurit.Service;

import com.example.springsecurit.Entity.User;
import com.example.springsecurit.dto.JwtAuthenticationResponse;
import com.example.springsecurit.dto.RefreshTokenRequest;
import com.example.springsecurit.dto.SignInRequest;
import com.example.springsecurit.dto.SignUpRequest;

public interface AuthenticationService {

    User signup(SignUpRequest signUpRequest);

    JwtAuthenticationResponse signin(SignInRequest signInRequest);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

}
