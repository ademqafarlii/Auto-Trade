package org.adem.autotrade.service;

import org.adem.autotrade.dto.auth.AuthenticationRequest;
import org.adem.autotrade.dto.auth.AuthenticationResponse;
import org.adem.autotrade.dto.auth.RegisterRequest;

public interface AuthenticationService {

    AuthenticationResponse register(RegisterRequest registerRequest);

    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
}
