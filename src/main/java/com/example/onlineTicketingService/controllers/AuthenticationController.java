package com.example.onlineTicketingService.controllers;

import com.example.onlineTicketingService.models.AuthenticationRequest;
import com.example.onlineTicketingService.models.AuthenticationResponse;
import com.example.onlineTicketingService.services.AuthenticationService;
import com.example.onlineTicketingService.models.RegisterRequest;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Builder
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
