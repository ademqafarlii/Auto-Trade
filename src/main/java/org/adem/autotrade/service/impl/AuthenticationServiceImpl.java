package org.adem.autotrade.service.impl;

import lombok.RequiredArgsConstructor;
import org.adem.autotrade.dto.auth.AuthenticationRequest;
import org.adem.autotrade.dto.auth.AuthenticationResponse;
import org.adem.autotrade.dto.auth.RegisterRequest;
import org.adem.autotrade.enums.Role;
import org.adem.autotrade.exception.EmailAlreadyInUseException;
import org.adem.autotrade.model.User;
import org.adem.autotrade.repository.UserRepository;
import org.adem.autotrade.security.JWTService;
import org.adem.autotrade.service.AuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JWTService jwtService;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public void register(RegisterRequest registerRequest) {
        Optional<User> existingUser = userRepository.findByEmail(registerRequest.getEmail());
        if (existingUser.isPresent()){
            throw new EmailAlreadyInUseException("Email already exists");
        }
        var user = User.builder()
                .name(registerRequest.getName())
                .surname(registerRequest.getSurname())
                .email(registerRequest.getEmail())
                .phoneNumber(registerRequest.getPhoneNumber())
                .password(bCryptPasswordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );
        var user = userRepository.findByEmail(authenticationRequest.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
