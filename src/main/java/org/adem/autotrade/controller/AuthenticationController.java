package org.adem.autotrade.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.adem.autotrade.aop.customAnnotation.ConsoleLog;
import org.adem.autotrade.dto.auth.AuthenticationRequest;
import org.adem.autotrade.dto.auth.AuthenticationResponse;
import org.adem.autotrade.dto.auth.RegisterRequest;
import org.adem.autotrade.service.AuthenticationService;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @PostMapping("/register")
    @ConsoleLog(value = "user registration")
    public void register(@RequestBody RegisterRequest registerRequest) {
        authenticationService.register(registerRequest);
    }

    @PostMapping("/authenticate")
    @ConsoleLog(value = "login")
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        return authenticationService.authenticate(authenticationRequest);
    }
}
